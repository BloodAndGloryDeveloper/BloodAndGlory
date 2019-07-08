package bloodandglory.client.tab;

import bloodandglory.common.registies.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemsTab extends CreativeTabs {
    public ItemsTab(){
        super("bloodandglory.item");
    }

    @Override
    public ItemStack createIcon(){
        return new ItemStack(ItemRegistry.MITHRIL);
    }
}
