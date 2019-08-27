package bloodandglory.common.registies;

import bloodandglory.ModInfo;
import bloodandglory.common.block.BlockBAG;
import bloodandglory.common.block.plant.BlockBAGCrops;
import bloodandglory.common.block.plant.BlockBAGMillet;
import bloodandglory.common.item.misc.ItemMisc.EnumItemMisc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;

public class BlockRegistry {
    public static final Set<Block> BLOCKS = new LinkedHashSet<>();
    public static final List<ItemBlock> ITEM_BLOCKS = new ArrayList<ItemBlock>();

    public static final Block MITHRIL_BLOCK = new BlockBAG(Material.IRON);
    public static final Block MITHRIL_ORE = new BlockBAG(Material.ROCK);

    public static final Block PLANT_COTTON = new BlockBAGCrops(ItemRegistry.COTTON, ItemRegistry.COTTON);
    public static final Block PLANT_MILLET = new BlockBAGMillet();

    public static void preInit(){
        try {
            for (Field field : BlockRegistry.class.getDeclaredFields()){
                if (field.get(null) instanceof Block){
                    Block block = (Block) field.get(null);
                    String name = field.getName();
                    registryBlock(block,name);
                }
            }
        }catch (IllegalAccessException ex){
            throw new RuntimeException(ex);
        }
    }

    private static void registryBlock(Block block,String fieldName){
        BLOCKS.add(block);
        String idName = fieldName.toLowerCase(Locale.ENGLISH);
        block.setRegistryName(idName).setTranslationKey(ModInfo.MOD_ID + "." + idName);

        ItemBlock item = null;
        if (block instanceof ICustomItemBlock){
            item = ((ICustomItemBlock)block).getItemBlock();
        }else {
            item = new ItemBlock(block);
        }
        if (item != null){
            ITEM_BLOCKS.add(item);
            item.setRegistryName(idName).setTranslationKey(ModInfo.MOD_ID + "." + idName);
        }
    }

    @SubscribeEvent
    public static void blockRegistry(final RegistryEvent.Register<Block> event){
        final IForgeRegistry<Block> registry = event.getRegistry();
        for (Block block : BLOCKS){
            registry.register(block);
        }
    }

    @SubscribeEvent
    public static void onHarvesting(BlockEvent.HarvestDropsEvent event){
        //如果破坏方块的实体不为空(即玩家或其他实体破坏，而不是爆炸)
        //getRNG设定掉落概率，我们设为1/10即可
        if (event.getHarvester() != null && event.getHarvester().getRNG().nextInt(10) == 0){
            //从丛林树叶掉落香蕉
            //判断是否是丛林树叶
            if (event.getState().getBlock() == Blocks.LEAVES && event.getState().getProperties().containsValue(BlockPlanks.EnumType.JUNGLE)){
                event.getDrops().add(new ItemStack(ItemRegistry.BANANA,2));
            }
            //从草丛掉落小米
            //判断是否是草丛
            if (event.getState().getBlock() == Blocks.TALLGRASS){
                event.getDrops().add(new ItemStack(ItemRegistry.MILLET));
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void modelRegistryBlock(ModelRegistryEvent event){
        try {
            for (Block block : BLOCKS){
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),0,new ModelResourceLocation(block.getRegistryName(),"inventory"));
            }
        }catch (NullPointerException ex){
            System.out.println("Sorry!It seems we haven't registry this block or something wrong happen.\n" +
                    "You can issues this problem to us on Github,sorry again");
        }
    }

    public interface ICustomItemBlock{
        @Nullable
        default ItemBlock getItemBlock(){
            return getDefaultItemBlock((Block) this);
        }

        static ItemBlock getDefaultItemBlock(Block block){
            if (Item.getItemFromBlock(block) != Items.AIR){
                return (ItemBlock) Item.getItemFromBlock(block);
            }else {
                return new ItemBlock(block);
            }
        }

        @SideOnly(Side.CLIENT)
        default ItemStack getRenderedItem() {
            return ItemStack.EMPTY;
        }
    }
}
