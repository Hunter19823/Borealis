package pie.ilikepiefoo2.notaurora;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("notaurora")
public class NotAurora {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "notaurora";
    public static final String MOD_NAME = "NotAurora";
    public static final int port = 48574;

    private static NotAuroraServer server;

    public NotAurora()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStart(FMLServerStartingEvent event)
    {
        start();
    }
    @SubscribeEvent
    public void onServerStopping(FMLServerStoppingEvent event)
    {
        stop();
    }

    public static void start()
    {
        if(server == null)
        {
            LOGGER.debug("Starting up server...");
            server = new NotAuroraServer(port);
            server.start();
        }
    }

    public static void stop()
    {
        if(server != null)
        {
            LOGGER.debug("Shutting down server...");
            server.shutdown();
            server = null;
        }
    }
}
