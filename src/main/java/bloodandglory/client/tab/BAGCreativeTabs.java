package bloodandglory.client.tab;

import bloodandglory.common.item.misc.ItemMisc;
import bloodandglory.common.registies.BlockRegistry;
import bloodandglory.common.registies.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BAGCreativeTabs {
    public static CreativeTabs ITEM_TAB = new CreativeTabs("bloodandglory.item") {
        @Override
        public ItemStack createIcon() {
            return ItemMisc.EnumItemMisc.MITHRIL.create(3);
        }
    };
    public static CreativeTabs TOOL_TAB = new CreativeTabs("bloodandglory.tool") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.MITHRIL_SWORD);
        }
    };
    public static CreativeTabs BLOCK_TAB = new CreativeTabs("bloodandglory.block") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockRegistry.MITHRIL_ORE);
        }
    };
}
