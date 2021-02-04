package pie.ilikepiefoo2.borealis.integration.kubejs;


import dev.latvian.kubejs.event.EventJS;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import pie.ilikepiefoo2.borealis.Borealis;



import java.util.Set;


public class KubeJSIntegration {
    private static final Logger LOGGER = LogManager.getLogger();

    public KubeJSIntegration()
    {
        LOGGER.info("FTBQuests Integration for "+ Borealis.MOD_NAME +" detected...");
        MinecraftForge.EVENT_BUS.addListener(KubeJSEventHandler::homePageEvent);
        MinecraftForge.EVENT_BUS.addListener(KubeJSEventHandler::onPageEvent);
        MinecraftForge.EVENT_BUS.addListener(KubeJSEventHandler::onServerStart);
        KubeJSHomePage.knownEventJSClasses.put("RecipeTypeRegistryEventJS", dev.latvian.kubejs.recipe.RecipeTypeRegistryEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("DebugInfoEventJS", dev.latvian.kubejs.client.DebugInfoEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("WorldEventJS", dev.latvian.kubejs.world.WorldEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("SpecialRecipeSerializerManager", dev.latvian.kubejs.recipe.special.SpecialRecipeSerializerManager.class);
        KubeJSHomePage.knownEventJSClasses.put("GameStageEventJS", dev.latvian.kubejs.integration.gamestages.GameStageEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ClientLoggedInEventJS", dev.latvian.kubejs.client.ClientLoggedInEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ChestEventJS", dev.latvian.kubejs.player.ChestEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("WorldgenAddEventJSForge", dev.latvian.kubejs.world.gen.forge.WorldgenAddEventJSForge.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemLeftClickEventJS", dev.latvian.kubejs.item.ItemLeftClickEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("LivingEntityEventJS", dev.latvian.kubejs.entity.LivingEntityEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemRightClickEmptyEventJS", dev.latvian.kubejs.item.ItemRightClickEmptyEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("EntitySpawnedEventJS", dev.latvian.kubejs.entity.EntitySpawnedEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("PlayerAdvancementEventJS", dev.latvian.kubejs.player.PlayerAdvancementEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("EntityEventJS", dev.latvian.kubejs.entity.EntityEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemPickupEventJS", dev.latvian.kubejs.item.ItemPickupEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("WorldgenRemoveEventJS", dev.latvian.kubejs.world.gen.WorldgenRemoveEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("HideJEIEventJS", dev.latvian.kubejs.integration.jei.HideJEIEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("BlockBreakEventJS", dev.latvian.kubejs.block.BlockBreakEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemTooltipEventJS", dev.latvian.kubejs.item.ItemTooltipEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("PlayerEventJS", dev.latvian.kubejs.player.PlayerEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemEntityInteractEventJS", dev.latvian.kubejs.item.ItemEntityInteractEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemCraftedEventJS", dev.latvian.kubejs.item.ItemCraftedEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("MissingMappingEventJS", dev.latvian.kubejs.block.forge.MissingMappingEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemRightClickEventJS", dev.latvian.kubejs.item.ItemRightClickEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("SimplePlayerEventJS", dev.latvian.kubejs.player.SimplePlayerEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("HideCustomJEIEventJS", dev.latvian.kubejs.integration.jei.HideCustomJEIEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("AddJEISubtypesEventJS", dev.latvian.kubejs.integration.jei.AddJEISubtypesEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("TagEventJS", dev.latvian.kubejs.server.TagEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("InventoryChangedEventJS", dev.latvian.kubejs.player.InventoryChangedEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("BlockLeftClickEventJS", dev.latvian.kubejs.block.BlockLeftClickEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("BlockLootEventJS", dev.latvian.kubejs.loot.BlockLootEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("CompostablesRecipeEventJS", dev.latvian.kubejs.recipe.CompostablesRecipeEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("InformationJEIEventJS", dev.latvian.kubejs.integration.jei.InformationJEIEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("BlockPlaceEventJS", dev.latvian.kubejs.block.BlockPlaceEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("WorldgenAddEventJS", dev.latvian.kubejs.world.gen.WorldgenAddEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("PlayerChatEventJS", dev.latvian.kubejs.player.PlayerChatEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemDestroyedEventJS", dev.latvian.kubejs.item.forge.ItemDestroyedEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ClientTickEventJS", dev.latvian.kubejs.client.ClientTickEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("CommandRegistryEventJS", dev.latvian.kubejs.command.CommandRegistryEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("DataPackEventJS", dev.latvian.kubejs.script.data.DataPackEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("RecipeEventJS", dev.latvian.kubejs.recipe.RecipeEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("BlockRightClickEventJS", dev.latvian.kubejs.block.BlockRightClickEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ExplosionEventJS", dev.latvian.kubejs.world.ExplosionEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("LivingEntityDeathEventJS", dev.latvian.kubejs.entity.LivingEntityDeathEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("SoundRegistryEventJS", dev.latvian.kubejs.client.SoundRegistryEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("LootEventJS", dev.latvian.kubejs.loot.LootEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("CheckLivingEntitySpawnEventJS", dev.latvian.kubejs.entity.forge.CheckLivingEntitySpawnEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("NetworkEventJS", dev.latvian.kubejs.net.NetworkEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("DataEvent", dev.latvian.kubejs.event.DataEvent.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemSmeltedEventJS", dev.latvian.kubejs.item.ItemSmeltedEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("CommandEventJS", dev.latvian.kubejs.server.CommandEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("BlockRegistryEventJS", dev.latvian.kubejs.block.BlockRegistryEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("BlockDropsEventJS", dev.latvian.kubejs.block.BlockDropsEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("OldItemTooltipEventJS", dev.latvian.kubejs.item.OldItemTooltipEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("Post", dev.latvian.kubejs.world.ExplosionEventJS.Post.class);
        KubeJSHomePage.knownEventJSClasses.put("ServerEventJS", dev.latvian.kubejs.server.ServerEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("LivingEntityAttackEventJS", dev.latvian.kubejs.entity.LivingEntityAttackEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemTossEventJS", dev.latvian.kubejs.item.ItemTossEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("FluidRegistryEventJS", dev.latvian.kubejs.fluid.FluidRegistryEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("SimpleWorldEventJS", dev.latvian.kubejs.world.SimpleWorldEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("InventoryEventJS", dev.latvian.kubejs.player.InventoryEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemFoodEatenEventJS", dev.latvian.kubejs.item.ItemFoodEatenEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("Pre", dev.latvian.kubejs.world.ExplosionEventJS.Pre.class);
        KubeJSHomePage.knownEventJSClasses.put("AddJEIEventJS", dev.latvian.kubejs.integration.jei.AddJEIEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("WorldgenRemoveEventJSForge", dev.latvian.kubejs.world.gen.forge.WorldgenRemoveEventJSForge.class);
        KubeJSHomePage.knownEventJSClasses.put("LivingEntityDropsEventJS", dev.latvian.kubejs.entity.forge.LivingEntityDropsEventJS.class);
        KubeJSHomePage.knownEventJSClasses.put("ItemRegistryEventJS", dev.latvian.kubejs.item.ItemRegistryEventJS.class);
    }

    /*
     * This method is used to generate a list of all the EventJS class locations using reflection.
     *
     */
    public static void main(String[] args)
    {
        Reflections reflections = new Reflections("dev.latvian.kubejs",new SubTypesScanner(false), new ResourcesScanner());
        Set<Class<? extends EventJS>> classes = reflections.getSubTypesOf(EventJS.class);
        for(Class eventClass : classes)
        {
            String link = "https://github.com/KubeJS-Mods/KubeJS/tree/master/common/src/main/java/"+eventClass.getName().replaceAll("\\.","/")+".java";
            System.out.println(KubeJSHomePage.class.getSimpleName()+".knownEventJSClasses.put(\""+eventClass.getSimpleName()+"\", "+eventClass.getCanonicalName()+".class);");
        }
    }
}
