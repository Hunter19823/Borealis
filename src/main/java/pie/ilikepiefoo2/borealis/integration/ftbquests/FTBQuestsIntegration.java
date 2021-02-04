package pie.ilikepiefoo2.borealis.integration.ftbquests;


import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pie.ilikepiefoo2.borealis.Borealis;

public class FTBQuestsIntegration {
    private static final Logger LOGGER = LogManager.getLogger();
    public FTBQuestsIntegration()
    {
        LOGGER.info("FTBQuests Integration for "+ Borealis.MOD_NAME +" detected...");
        MinecraftForge.EVENT_BUS.addListener(FTBQuestsEventHandler::homePageEvent);
        MinecraftForge.EVENT_BUS.addListener(FTBQuestsEventHandler::onPageEvent);
        MinecraftForge.EVENT_BUS.addListener(FTBQuestsEventHandler::onPlayerJoin);
        MinecraftForge.EVENT_BUS.addListener(FTBQuestsEventHandler::onPlayerLeave);
    }
}
