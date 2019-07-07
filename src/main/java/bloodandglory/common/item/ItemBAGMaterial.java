package bloodandglory.common.item;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBAGMaterial {
    public static final ToolMaterial TOOL_MITHRIL = EnumHelper.addToolMaterial("mithril",3,1009,10.0F,5.0F,30);
    public static final ToolMaterial TOOL_BRONZE = EnumHelper.addToolMaterial("bronze",2,239,9.0F,2.0F,8);
    public static final ToolMaterial TOOL_STEEL = EnumHelper.addToolMaterial("steel",2,500,7.0F,4.0F,17);
    public static final ToolMaterial TOOL_STEELMAKING = EnumHelper.addToolMaterial("steelmaking",3,700,8.0F,5.0F,20);
}
