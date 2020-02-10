package bloodandglory.client.tab;

import bloodandglory.common.registies.BlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlocksTab extends CreativeTabs {
    public BlocksTab(){
        super("bloodandglory.block");
    }

    @Override
    public ItemStack getTabIconItem(){
        return new ItemStack(BlockRegistry.MITHRIL_ORE);
    }
}
