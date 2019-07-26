package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import bloodandglory.common.item.ItemBAGMaterial;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBAGHoe extends ItemHoe {
    public ItemBAGHoe(ToolMaterial material){
        super(material);
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        ToolInformation.addToolInformation(tooltip, this);
        if (this.toolMaterial == ItemBAGMaterial.TOOL_MITHRIL){
            tooltip.add(I18n.format("tooltip.bloodandglory.mithril_tool"));
        }
    }
}
