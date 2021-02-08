package pie.ilikepiefoo2.borealis;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static pie.ilikepiefoo2.borealis.Borealis.MOD_ID;

@Mod(MOD_ID)
public class Borealis {
    public static final String MOD_ID = "borealis";
    public static final String MOD_NAME = "Borealis";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    private static BorealisServer server;

    public Borealis()
    {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BorealisConfigHandler.COMMON_SPEC);
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
            server = new BorealisServer(BorealisConfigHandler.COMMON.borealisPort.get(),minecraftServer);
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
