package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemBAGSword extends ItemSword {
    public ItemBAGSword(ToolMaterial material){
        super(material);
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }
}
