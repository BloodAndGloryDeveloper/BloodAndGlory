package bloodandglory.common.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * 旨在将抽象类WorldGenerator具体化
 * 以便在Mod大量复用*/
public class WorldGeneratorGlowstone extends WorldGenerator {

    @Override
    public boolean generate(World world, Random random, BlockPos pos){

        return true;
    }
}
