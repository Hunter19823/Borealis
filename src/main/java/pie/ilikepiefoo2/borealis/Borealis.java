package pie.ilikepiefoo2.borealis;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pie.ilikepiefoo2.borealis.integration.kubejs.KubeJSHomePage;
import pie.ilikepiefoo2.borealis.integration.ftbquests.FTBQuestsIntegration;
import pie.ilikepiefoo2.borealis.integration.kubejs.KubeJSIntegration;

import static pie.ilikepiefoo2.borealis.Borealis.MOD_ID;

@Mod(MOD_ID)
public class Borealis {
    public static final Logger LOGGER = LogManager.getLogger("Borealis");
    public static final String MOD_ID = "borealis";
    public static final String MOD_NAME = "Borealis";

    public static final int port = 48574;

    private static BorealisServer server;

    public Borealis()
    {
        MinecraftForge.EVENT_BUS.register(this);
        if(ModList.get().isLoaded("kubejs"))
        {
            new KubeJSIntegration();
        }
        if(ModList.get().isLoaded("ftbquests"))
        {
            new FTBQuestsIntegration();
        }
    }

    public static BorealisServer getServer()
    {
        return server;
    }

    @SubscribeEvent
    public void onServerStart(FMLServerStartingEvent event)
    {
        start(event.getServer());
    }

    @SubscribeEvent
    public void onServerStopping(FMLServerStoppingEvent event)
    {
        stop();
    }

    public static void start(MinecraftServer minecraftServer)
    {
        if(server == null)
        {
            LOGGER.debug("Starting up server...");
            server = new BorealisServer(port,minecraftServer);
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
