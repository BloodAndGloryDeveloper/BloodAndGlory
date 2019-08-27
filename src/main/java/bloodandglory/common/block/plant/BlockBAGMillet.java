package bloodandglory.common.block.plant;

import bloodandglory.common.registies.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraft.block.BlockCrops;

import java.util.Random;

public class BlockBAGMillet extends BlockReed implements IPlantable {
    protected static final AxisAlignedBB MILLET_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);
    public BlockBAGMillet(){

    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return MILLET_AABB;
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
