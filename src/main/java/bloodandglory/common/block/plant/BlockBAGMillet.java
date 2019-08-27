package bloodandglory.common.block.plant;

import bloodandglory.common.block.BlockBAG;
import bloodandglory.common.registies.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockBAGMillet extends BlockReed implements IPlantable {
    public BlockBAGMillet(){

    }

    /**
     * 不得不重写整个判断方法
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos.down());
        Block block = state.getBlock();
        //查看方块是否支持植物生长
        if (block.canSustainPlant(state,worldIn,pos.down(), EnumFacing.UP,this)){
            return true;
        }
        //耕地上方
        if (block == Blocks.FARMLAND){
            return true;
        }else {
            BlockPos blockPos = pos.down();
            for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL){
                //检查周围的方块是否为水
                IBlockState iBlockState = worldIn.getBlockState(blockPos.offset(facing));
                if (iBlockState == Blocks.WATER.getDefaultState()){
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemRegistry.MILLET;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ItemRegistry.MILLET);
    }
}
