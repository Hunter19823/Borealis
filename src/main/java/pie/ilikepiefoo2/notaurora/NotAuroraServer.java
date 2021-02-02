package pie.ilikepiefoo2.notaurora;


import dev.latvian.mods.aurora.PageType;
import dev.latvian.mods.aurora.page.WebPage;
import dev.latvian.mods.aurora.page.WebPageNotFound;
import dev.latvian.mods.aurora.page.WebPageUnauthorized;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import net.minecraftforge.common.MinecraftForge;
import pie.ilikepiefoo2.notaurora.page.HomePage;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author LatvianModder
 *
 * Modified by Pie.
 * Original: https://github.com/LatvianModder/Aurora/blob/master/src/main/java/dev/latvian/mods/aurora/AuroraServer.java
 */
public class NotAuroraServer
{
    private final int port;
    private ChannelFuture channel;
    private final EventLoopGroup masterGroup;
    private final EventLoopGroup slaveGroup;

    private byte[] iconBytes = null;

    public NotAuroraServer(int p)
    {
        port = p;
        masterGroup = new NioEventLoopGroup();
        slaveGroup = new NioEventLoopGroup();
    }

    void start()
    {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        try
        {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(masterGroup, slaveGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>()
            {
                @Override
                public void initChannel(final SocketChannel ch)
                {
                    ch.pipeline().addLast("codec", new HttpServerCodec());
                    ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512 * 1024));
                    ch.pipeline().addLast("request", new ChannelInboundHandlerAdapter()
                    {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
                        {
                            if (msg instanceof FullHttpRequest)
                            {
                                handleRequest(ch, ctx, (FullHttpRequest) msg);
                            }
                            else
                            {
                                super.channelRead(ctx, msg);
                            }
                        }

                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx)
                        {
                            ctx.flush();
                        }

                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
                        {
                            ctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, Unpooled.copiedBuffer(cause.getMessage().getBytes())));
                        }
                    });
                }
            });

            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            channel = bootstrap.bind(port).sync();
        }
        catch (InterruptedException ignored)
        {
        }
    }

    void shutdown()
    {
        slaveGroup.shutdownGracefully();
        masterGroup.shutdownGracefully();

        try
        {
            channel.channel().closeFuture().sync();
        }
        catch (InterruptedException ignored)
        {
        }
    }

    private void handleRequest(SocketChannel channel, ChannelHandlerContext ctx, FullHttpRequest request)
    {
        String uri = request.uri();
        WebPage page;

        while (uri.startsWith("/"))
        {
            uri = uri.substring(1);
        }

        while (uri.endsWith("/"))
        {
            uri = uri.substring(0, uri.length() - 1);
        }

        if (uri.isEmpty())
        {
            page = new HomePage(this);
        }
        else
        {
            try
            {
                AuroraPageEvent event = new AuroraPageEvent(this, request, uri);
                MinecraftForge.EVENT_BUS.post(event);
                page = event.getPage();

                if (page != null && page.getPageType() != PageType.ENABLED && !System.getProperty("AuroraIgnoreAuth", "0").equals("1"))
                {
                    page = new WebPageUnauthorized(event.getUri());
                }

                if (page == null)
                {
                    page = new WebPageNotFound(event.getUri());
                }
            }
            catch (Exception ex)
            {
                page = new WebPageNotFound("errored");
                ex.printStackTrace();
            }
        }

        String content;
        String contentType;

        try
        {
            content = page.getContent();
            contentType = page.getContentType();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println("Error!");
            printWriter.println();
            ex.printStackTrace(printWriter);
            content = writer.toString();
            contentType = "text/plain";
        }

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, page.getStatus(), Unpooled.copiedBuffer(content.getBytes()));

        if (HttpUtil.isKeepAlive(request))
        {
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.length());
        response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        ctx.writeAndFlush(response);
    }

    public boolean allow(String uri)
    {
        return true;
    }
}
