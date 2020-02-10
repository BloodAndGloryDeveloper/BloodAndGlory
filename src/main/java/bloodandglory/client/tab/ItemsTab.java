package bloodandglory.client.tab;

import bloodandglory.common.item.misc.ItemMisc;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemsTab extends CreativeTabs {
    public ItemsTab(){
        super("bloodandglory.item");
    }

    @Override
    public ItemStack getTabIconItem(){
        return ItemMisc.EnumItemMisc.MITHRIL.create(1);
    }
}
