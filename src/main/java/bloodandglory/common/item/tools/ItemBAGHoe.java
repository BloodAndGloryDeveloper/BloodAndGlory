package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.item.ItemHoe;

public class ItemBAGHoe extends ItemHoe {
    public ItemBAGHoe(ToolMaterial material){
        super(material);
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }
}
