package bloodandglory.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IGenericItem {
    /**
     * @return Item's translation key
     * */
    String getUnlocalizedName();

    String getModelName();
    /**
     * @return This Item's ID(Damage)
     * */
    int getID();

    Item getItem();

    default ItemStack create(int size){
        return new ItemStack(this.getItem(),size,this.getID());
    }

    default boolean isItemOf(ItemStack stack){
        return !stack.isEmpty() && stack.getItem() == this.getItem() && stack.getItemDamage() == this.getID();
    }

    default boolean isItemEmpty(IGenericItem item){
        return item != null && item.getItem() == this.getItem() && item.getID() == this.getID();
    }

    static class TypeContainer {
        private final Map<Class<? extends Enum<?>>, List<IGenericItem>> typeToItems = new HashMap<Class<? extends Enum<?>>, List<IGenericItem>>();
    }
    static TypeContainer TYPE_CONTAINER = new TypeContainer();

    public static List<IGenericItem> getGenericItems(Class<? extends Enum<?>> type) {
        List<IGenericItem> genericItems = TYPE_CONTAINER.typeToItems.get(type);
        if(genericItems == null) {
            if(!IGenericItem.class.isAssignableFrom(type))
                throw new RuntimeException("Type " + type + " does not implement IGenericItem");
            TYPE_CONTAINER.typeToItems.put(type, genericItems = new ArrayList<IGenericItem>());
            for(Object item : type.getEnumConstants())
                genericItems.add((IGenericItem)item);
        }
        return genericItems;
    }

    public static IGenericItem getFromID(Class<? extends Enum<?>> type, int id) {
        for(IGenericItem item : getGenericItems(type)) {
            if(item.getID() == id)
                return item;
        }
        return null;
    }

    public static IGenericItem getFromStack(Class<? extends Enum<?>> type, ItemStack stack) {
        return stack.isEmpty() ? null : getFromID(type, stack.getItemDamage());
    }
}
