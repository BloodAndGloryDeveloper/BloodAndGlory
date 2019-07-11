package bloodandglory.common.world.gen;

import bloodandglory.common.registies.BlockRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OreGenEventHandler {
    private static WorldGenerator oreMithrilGen = new WorldGenMinable(BlockRegistry.MITHRIL_ORE.getDefaultState(),16);

    @SubscribeEvent
    public static void onOreGen(OreGenEvent.Post event){
        //在这里注册要生成的矿物
        genOre(event,oreMithrilGen,16,16);
    }
    /**
     * 创建一个通用的矿物生成方法，矿物生成高度区间为[oreGenStart,oreGenHigh + oreGenStart)
     * @param oreGenHigh 设置矿物生成高度，这里是从oreGenStart开始算的
     * @param oreGenStart 设置矿物生成最低高度
     */
    public static void genOre(OreGenEvent.Post event,WorldGenerator worldGenerator,int oreGenHigh,int oreGenStart){
        //检查矿物生成
        if (TerrainGen.generateOre(event.getWorld(),event.getRand(),worldGenerator,event.getPos(),OreGenEvent.GenerateMinable.EventType.CUSTOM)){
            for (int i = 0;i < 4;i++){
                //产生矿物坐标
                int posX = event.getPos().getX() + event.getRand().nextInt(16);
                int posY = oreGenStart + event.getRand().nextInt(oreGenHigh);
                int posZ = event.getPos().getZ() + event.getRand().nextInt(16);
                BlockPos blockPos = new BlockPos(posX,posY,posZ);
                //生成矿物
                worldGenerator.generate(event.getWorld(),event.getRand(),blockPos);
            }
        }
    }
}
