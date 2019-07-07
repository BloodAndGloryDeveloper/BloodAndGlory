package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ItemBAGPickaxe extends ItemPickaxe {
    public ItemBAGPickaxe(ToolMaterial material){
        super(material);
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }
}
