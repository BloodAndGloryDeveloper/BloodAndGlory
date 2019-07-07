package bloodandglory.common.registies;

import bloodandglory.common.item.ItemMisc;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**在此注册物品*/
public class ItemRegistry {
    public static final Set<Item> ITEMS = new LinkedHashSet<>();

    public static final Item MITHRIL = new ItemMisc();
    public static final Item BRONZE = new ItemMisc();
    public static final Item STEELMAKING = new ItemMisc();
    public static final Item IMMORTAL_CUPRUM = new ItemMisc();//仙铜
    public static final Item STEEL = new ItemMisc();
    //货币Currency
    public static final Item NOLDOR_CURRENCY = new ItemMisc();
    public static final Item EMPIRE_CURRENCY = new ItemMisc();
    public static final Item HARD_CURRENCY = new ItemMisc();


    public static void preInit(){
        try {
            for (Field field:ItemRegistry.class.getDeclaredFields()){
                if (field.get(null) instanceof Item){
                    Item item = (Item) field.get(null);
                    registrItems(item,field.getName());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void registrItems(Item item,String fieldName){
        ITEMS.add(item);
        String idName = fieldName.toLowerCase(Locale.ENGLISH);
        item.setRegistryName(idName).setUnlocalizedName("bloodandglory."+ idName);

    }

    @SubscribeEvent
    public static void itemRegister(final RegistryEvent.Register<Item> event){
        final IForgeRegistry<Item> registry = event.getRegistry();
        for (Item item : ITEMS){
            registry.register(item);

        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void modelRegisterItems(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
        }
    }
}