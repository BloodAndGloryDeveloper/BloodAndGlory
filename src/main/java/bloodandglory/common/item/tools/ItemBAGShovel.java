package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import bloodandglory.common.item.ItemBAGMaterial;
import com.sun.istack.internal.NotNull;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * 沙雕Mojang为什么把Shovel命名为Spade，自己又在原版命名为Shovel啊摔!*/
public class ItemBAGShovel extends ItemSpade {
    public ItemBAGShovel(ToolMaterial material){
        super(material);
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }

    @Override
    public void addInformation(ItemStack stack, @NotNull World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        ToolInformation.addToolInformation(tooltip, this);
        if (this.toolMaterial == ItemBAGMaterial.TOOL_MITHRIL){
            tooltip.add(I18n.format("tooltip.bloodandglory.mithril_tool"));
        }
    }
}
