package bloodandglory.common.world.gen;

import bloodandglory.common.world.WorldGeneratorGlowstone;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OreGenLoader {
    private static WorldGenerator generatorOre = new WorldGeneratorGlowstone();

    @SubscribeEvent
    public static void onOreGen(OreGenEvent.Post event){

    }
}
