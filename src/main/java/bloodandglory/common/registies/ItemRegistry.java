package bloodandglory.common.registies;

import bloodandglory.BloodAndGlory;
import bloodandglory.ModInfo;
import bloodandglory.common.item.ItemBAGMaterial;
import bloodandglory.common.item.misc.ItemMisc;
import bloodandglory.common.item.misc.ItemAlcohol;
import bloodandglory.common.item.food.ItemBAGFood;
import bloodandglory.common.item.food.ItemRise;
import bloodandglory.common.item.tools.*;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**在此注册物品*/
public class ItemRegistry {
    //存放物品的哈希表
    public static final Set<Item> ITEMS = new LinkedHashSet<>();

    public static final Item ITEM_MISC = new ItemMisc();

    //Tool
    public static final Item MITHRIL_AXE = new ItemBAGAxe(ItemBAGMaterial.TOOL_MITHRIL);
    public static final Item MITHRIL_SWORD = new ItemBAGSword(ItemBAGMaterial.TOOL_MITHRIL);
    public static final Item MITHRIL_SHOVEL = new ItemBAGShovel(ItemBAGMaterial.TOOL_MITHRIL);
    public static final Item MITHRIL_PICKAXE = new ItemBAGPickaxe(ItemBAGMaterial.TOOL_MITHRIL);
    public static final Item MITHRIL_HOE = new ItemBAGHoe(ItemBAGMaterial.TOOL_MITHRIL);
    //Food
    public static final Item ALCOHOL = new ItemAlcohol();
    public static final Item COOKED_EGG = new ItemBAGFood(4,2.0F,true);
    public static final Item RAW_RICE = new ItemRise(2,0.2F,false).setPotionEffect(new PotionEffect(MobEffects.HUNGER,500,0),0.4F);
    public static final Item RICE = new ItemRise(7,0.5F,false);
    public static final Item BANANA = new ItemBAGFood(3,0.7F,false);

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
            BloodAndGlory.proxy.registerDefaultItemRenderer(item);
        }
    }

    public interface IMultipleItemModelDefinition {
        /**
         * A map from item meta values to different item models
         * @return
         */
        @SideOnly(Side.CLIENT)
        Map<Integer, ResourceLocation> getModels();
    }

    public interface ICustomMeshCallback {

        /**
         * A callback to get a custom mesh definition
         * @return
         */
        @SideOnly(Side.CLIENT)
        ItemMeshDefinition getMeshDefinition();

    }
}