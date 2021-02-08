package pie.ilikepiefoo2.borealis;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.stream.Collectors;

public final class BorealisConfigHandler {

    public static class Common {
        public final ForgeConfigSpec.IntValue borealisPort;

        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("borealisPort");
            borealisPort = builder
                    .comment("Borealis Webserver Port. Default: 48574")
                    .defineInRange("borealisPort",48574, 1025, 65534);
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

}
