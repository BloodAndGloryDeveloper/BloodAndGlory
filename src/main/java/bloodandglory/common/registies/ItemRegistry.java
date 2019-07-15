package bloodandglory.common.registies;

import bloodandglory.ModInfo;
import bloodandglory.common.item.ItemBAGMaterial;
import bloodandglory.common.item.misc.ItemMisc;
import bloodandglory.common.item.misc.ItemAlcohol;
import bloodandglory.common.item.food.ItemBAGFood;
import bloodandglory.common.item.food.ItemRise;
import bloodandglory.common.item.tools.*;
import com.sun.istack.internal.NotNull;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
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
import java.util.List;
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
    public static final Item IMMORTAL_CUPRUM = new ItemMisc();  //仙铜
    public static final Item STEEL = new ItemMisc();
    public static final Item BRIGHT_FEATHER = new ItemMisc();
    public static final Item SWAN_QUILL = new ItemMisc();
    public static final Item HEART_OF_STORM = new ItemMisc();
    public static final Item STONE_OF_FALCON = new ItemMisc();

    public static final Item MILLET  =new ItemMisc();
    public static final Item COTTON = new ItemMisc();
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
    //Food
    public static final Item ALCOHOL = new ItemAlcohol();
    public static final Item COOKED_EGG = new ItemBAGFood(4,2.0F,true);
    public static final Item RAW_RICE = new ItemRise(2,0.2F,false).setPotionEffect(new PotionEffect(MobEffects.HUNGER,500,0),0.4F);
    public static final Item RICE = new ItemRise(7,0.5F,false);
    public static final Item BANANA = new ItemBAGFood(3,0.7F,false);



    //添加烤制
    public static void addSmelting(){
        GameRegistry.addSmelting(BlockRegistry.MITHRIL_ORE,new ItemStack(MITHRIL),1.0F);
        GameRegistry.addSmelting(Items.EGG,new ItemStack(COOKED_EGG),0.5F);
        GameRegistry.addSmelting(RAW_RICE,new ItemStack(RICE),0.5F);
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