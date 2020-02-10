package bloodandglory.common.item.misc;

import bloodandglory.ModInfo;
import bloodandglory.client.tab.BAGCreativeTabs;
import bloodandglory.common.item.IGenericItem;
import bloodandglory.common.registies.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 杂项*/
public class ItemMisc extends Item implements ItemRegistry.IMultipleItemModelDefinition {
    public ItemMisc(){
        super();
        this.setCreativeTab(BAGCreativeTabs.ITEM_TAB);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        try {
            return "item.bloodandglory." + IGenericItem.getFromStack(EnumItemMisc.class,stack).getUnlocalizedName();
        }catch (Exception ex){
            return "item.bloodandglory.null_name";
        }
    }

    @Override
    public Map<Integer, ResourceLocation> getModels(){
        Map<Integer, ResourceLocation> models = new HashMap<>();
        for (EnumItemMisc item : EnumItemMisc.values()){
            models.put(item.getID(), new ResourceLocation(ModInfo.MOD_ID,item.getModelName()));
        }
        return models;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> item){
        if (this.isInCreativeTab(tab)){
            Stream.of(EnumItemMisc.values()).forEach(t -> item.add(t.create(1)));
        }
    }

    public enum EnumItemMisc implements IGenericItem{
        NOLDOR_CURRENCY(0),
        EMPIRE_CURRENCY(1),
        JIAOZI(3),
        MITHRIL(4),
        BRONZE(5),
        STEELMAKING(6),
        IMMORTAL_CUPRUM(7),  //仙铜
        STEEL(8),
        BRIGHT_FEATHER(9),
        SWAN_QUILL(10),
        HEART_OF_STORM(11),
        STONE_OF_FALCON(12),
        MILLET(13),
        COTTON(14);

        private final int id;
        private final String translationKey;
        private final String modelName;

        EnumItemMisc(int id){
            this.id = id;
            this.modelName = this.name().toLowerCase(Locale.ENGLISH);
            this.translationKey = this.modelName;
        }

        @Override
        public String getUnlocalizedName() {
            return translationKey;
        }
        @Override
        public int getID() {
            return id;
        }
        @Override
        public String getModelName(){
            return modelName;
        }
        @Override
        public Item getItem(){
            return ItemRegistry.ITEM_MISC;
        }
    }
}
