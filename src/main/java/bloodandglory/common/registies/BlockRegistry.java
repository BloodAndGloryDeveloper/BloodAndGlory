package bloodandglory.common.registies;

import bloodandglory.ModInfo;
import bloodandglory.common.block.BlockBAG;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;
import java.util.*;

public class BlockRegistry {
    public static final Set<Block> BLOCKS = new LinkedHashSet<>();
    public static final List<ItemBlock> ITEM_BLOCKS = new ArrayList<ItemBlock>();

    public static final Block MITHRIL_BLOCK = new BlockBAG(Material.IRON);
    public static final Block MITHRIL_ORE = new BlockBAG(Material.ROCK);

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

        ItemBlock item = new ItemBlock(block);
        ITEM_BLOCKS.add(item);
        item.setRegistryName(idName).setTranslationKey(ModInfo.MOD_ID + "." + idName);
    }

    @SubscribeEvent
    public static void blockRegistry(final RegistryEvent.Register<Block> event){
        final IForgeRegistry<Block> registry = event.getRegistry();
        for (Block block : BLOCKS){
            registry.register(block);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void modelRegistryBlock(ModelRegistryEvent event){
        for (Block block : BLOCKS){
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),0,new ModelResourceLocation(block.getRegistryName(),"inventory"));
        }
    }
}
