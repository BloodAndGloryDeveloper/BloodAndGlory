package bloodandglory.common.registies;

import bloodandglory.ModInfo;
import bloodandglory.common.item.ItemBAGMaterial;
import bloodandglory.common.item.ItemMisc;
import bloodandglory.common.item.tools.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**在此注册物品*/
public class ItemRegistry {
    //存放物品的哈希表
    public static final Set<Item> ITEMS = new LinkedHashSet<>();
    //材料
    public static final Item MITHRIL = new ItemMisc();
    public static final Item BRONZE = new ItemMisc();
    public static final Item STEELMAKING = new ItemMisc();
    public static final Item IMMORTAL_CUPRUM = new ItemMisc();//仙铜
    public static final Item STEEL = new ItemMisc();
    //货币Currency
    public static final Item NOLDOR_CURRENCY = new ItemMisc();
    public static final Item EMPIRE_CURRENCY = new ItemMisc();
    public static final Item HARD_CURRENCY = new ItemMisc();
    //Tool
    public static final Item MITHRIL_AXE = new ItemBAGAxe(ItemBAGMaterial.TOOL_MITHRIL);
    public static final Item MITHRIL_SWORD = new ItemBAGSword(ItemBAGMaterial.TOOL_MITHRIL);
    public static final Item MITHRIL_SHOVEL = new ItemBAGShovel(ItemBAGMaterial.TOOL_MITHRIL);
    public static final Item MITHRIL_PICKAXE = new ItemBAGPickaxe(ItemBAGMaterial.TOOL_MITHRIL);
    public static final Item MITHRIL_HOE = new ItemBAGHoe(ItemBAGMaterial.TOOL_MITHRIL);


    public static void addSmelting(){
        GameRegistry.addSmelting(BlockRegistry.MITHRIL_ORE,new ItemStack(MITHRIL),1.0F);
    }

    public static void preInit(){
        try {
            for (Field field:ItemRegistry.class.getDeclaredFields()){
                if (field.get(null) instanceof Item){
                    Item item = (Item) field.get(null);
                    registryItems(item,field.getName());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void registryItems(Item item,String fieldName){
        ITEMS.add(item);
        String idName = fieldName.toLowerCase(Locale.ENGLISH);
        item.setRegistryName(idName).setTranslationKey(ModInfo.MOD_ID + "." + idName);

    }

    @SubscribeEvent
    public static void itemRegister(final RegistryEvent.Register<Item> event){
        final IForgeRegistry<Item> registry = event.getRegistry();
        for (Item item : BlockRegistry.ITEM_BLOCKS){
            registry.register(item);
        }
        for (Item item : ITEMS){
            registry.register(item);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void modelRegisterItems(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            //fixme 这个实现太过简陋了
            ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
        }
    }
}