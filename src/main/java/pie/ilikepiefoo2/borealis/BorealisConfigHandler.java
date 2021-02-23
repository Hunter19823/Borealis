package pie.ilikepiefoo2.borealis;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import pie.ilikepiefoo2.borealis.page.PageType;

import java.util.Collections;
import java.util.List;

public final class BorealisConfigHandler {

    public static class Common {
        public final ForgeConfigSpec.IntValue borealisPort;
        public final ForgeConfigSpec.EnumValue<PageType> modListPage;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedMods;
        public final ForgeConfigSpec.EnumValue<PageType> playerListPage;
        public final ForgeConfigSpec.EnumValue<PageType> playerListJSON;
        public final ForgeConfigSpec.EnumValue<PageType> worldInfoJSON;
        public final ForgeConfigSpec.EnumValue<PageType> jsonUtilPage;

        public Common(ForgeConfigSpec.Builder builder)
        {

            builder.push("borealisPort");
            borealisPort = builder
                    .comment("Borealis Webserver Port. Default: 48574")
                    .defineInRange("borealisPort",48574, 1025, 65534);
            builder.pop();
            builder.push("modListPage");
            modListPage = builder
                    .comment("Enable/Disable ModList Page.")
                    .defineEnum("modListPage",PageType.ENABLED,     PageType.values());
            blacklistedMods = builder
                    .comment("List of modids to blacklist from the mod list page.\n"+
                            "Example of hiding minecraft and forge on the mod list:\n"+
                            "blacklistedMods = [\"minecraft\",\"forge\"]"
                    )
                    .defineList("blacklistedMods",
                            Collections.emptyList(),
                            s -> s instanceof String && ResourceLocation.tryCreate((String) s) != null
                    );
            builder.pop();
            builder.push("playerListPage");
            playerListPage = builder
                    .comment("Enable/Disable PlayerList Page.")
                    .defineEnum("playerListPage",PageType.ENABLED,  PageType.values());
            builder.pop();
            builder.push("jsonUtilPage");
            jsonUtilPage = builder
                    .comment("Enable/Disable jsonUtil Page.")
                    .defineEnum("jsonUtilPage",PageType.ENABLED,   PageType.values());
            builder.pop();
            builder.push("playerListJSON");
            playerListJSON = builder
                    .comment("Enable/Disable PlayerList JSON.")
                    .defineEnum("playerListJSON",PageType.ENABLED,  PageType.values());
            builder.pop();
            builder.push("worldInfoJSON");
            worldInfoJSON = builder
                    .comment("Enable/Disable worldInfo JSON.")
                    .defineEnum("worldInfoJSON",PageType.ENABLED,   PageType.values());
            builder.pop();

        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static void onConfigLoad()
    {
        //blacklistedMods = COMMON.blacklistedMods.get().stream().map(ResourceLocation::new).collect(Collectors.toSet());
    }

}
