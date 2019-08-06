package bloodandglory.common.item.misc;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.item.ItemArrow;

public class ItemBAGArrow extends ItemArrow {
    public ItemBAGArrow(){
        super();
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }
}
