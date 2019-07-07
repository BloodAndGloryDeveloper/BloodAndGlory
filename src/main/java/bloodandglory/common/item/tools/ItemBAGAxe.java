package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.item.ItemAxe;

public class ItemBAGAxe extends ItemAxe {
    public ItemBAGAxe(ToolMaterial material){
        super(material,5.0F+material.getAttackDamage(),-0.1F);
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }
}