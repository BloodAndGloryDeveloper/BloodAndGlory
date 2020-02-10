package bloodandglory.client.tab;

import bloodandglory.common.registies.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ToolsTab extends CreativeTabs {
    public ToolsTab(){
        super("bloodandglory.tool");
    }
    @Override
    public ItemStack getTabIconItem(){
        return new ItemStack(ItemRegistry.MITHRIL_SWORD);
    }
}
