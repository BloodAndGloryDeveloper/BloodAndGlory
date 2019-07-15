package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import bloodandglory.common.item.ItemBAGMaterial;
import bloodandglory.common.registies.ItemRegistry;
import com.sun.istack.internal.NotNull;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import net.minecraft.world.World;

import java.util.List;

public class ItemBAGAxe extends ItemAxe {
    public ItemBAGAxe(ToolMaterial material){
        super(material,material.getAttackDamage(),-0.1F);
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