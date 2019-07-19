package bloodandglory.common.block.tile;

import bloodandglory.client.tab.BAGCreativeTabs;
import bloodandglory.common.registies.BlockRegistry;
import bloodandglory.common.registies.BlockRegistry.ICustomItemBlock;
import bloodandglory.common.tile.TileEntityBAGFurnace;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockBAGFurnace extends BlockFurnace implements ICustomItemBlock {
    private final boolean isBurning;                                            // 是否正在燃烧
    private static boolean keepInventory;                                       // 检查有无物品存储
    public static final PropertyDirection FACING = BlockHorizontal.FACING;      // 设置方块朝向

    public BlockBAGFurnace(boolean isBurning){
        super(isBurning);
        this.isBurning = isBurning;
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)); // 设置向北为默认朝向
        if (!isBurning){
            this.setCreativeTab(BAGCreativeTabs.BLOCK_TAB);
        }
    }

    @Override
    public ItemStack getItem(World world,BlockPos pos,IBlockState state){
        return new ItemStack(BlockRegistry.INDUSTRIAL_BOILER);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityBAGFurnace();
    }

    @Override
    public ItemBlock getItemBlock(){
        if (this.isBurning){
            return null;
        }
        return ICustomItemBlock.getDefaultItemBlock(this);
    }

}
