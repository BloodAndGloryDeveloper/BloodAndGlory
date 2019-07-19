package bloodandglory.common.block.tile;

import bloodandglory.client.tab.BAGCreativeTabs;
import bloodandglory.common.registies.BlockRegistry;
import bloodandglory.common.registies.BlockRegistry.ICustomItemBlock;
import bloodandglory.common.tile.TileEntityBAGFurnace;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBAGFurnace extends BlockContainer implements ICustomItemBlock {
    private final boolean isBurning;                                            // 是否正在燃烧
    private static boolean keepInventory;                                       // 检查有无物品存储
    public static final PropertyDirection FACING = BlockHorizontal.FACING;      // 设置方块朝向

    public BlockBAGFurnace(boolean isBurning){
        super(Material.ROCK);
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

    @Override
    public ItemStack getRenderedItem() {
        return new ItemStack(BlockRegistry.INDUSTRIAL_BOILER);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote){
            return false;
        }else {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (tileEntity instanceof TileEntityBAGFurnace){
                playerIn.displayGUIChest((TileEntityBAGFurnace) tileEntity);
                playerIn.addStat(StatList.FURNACE_INTERACTION);
            }
            return true;
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityBAGFurnace furnace = (TileEntityBAGFurnace) worldIn.getTileEntity(pos);

//todo:待添加
        super.breakBlock(worldIn, pos, state);
    }
}
