package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import bloodandglory.common.item.misc.ItemBAGArrow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;


public class ItemCrossbow extends ItemBow {
    public ItemCrossbow(){
        super();
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
        this.setMaxDamage(400);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }


    @Override
    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() instanceof ItemBAGArrow;
    }
}
