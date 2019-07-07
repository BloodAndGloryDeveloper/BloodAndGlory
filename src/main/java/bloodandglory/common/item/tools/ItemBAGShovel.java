package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.item.ItemSpade;
/**
 * 沙雕Mojang为什么把Shovel命名为Spade，自己又在原版命名为Shovel啊摔!*/
public class ItemBAGShovel extends ItemSpade {
    public ItemBAGShovel(ToolMaterial material){
        super(material);
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }
}
