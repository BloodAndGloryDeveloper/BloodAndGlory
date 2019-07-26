package bloodandglory.common.item.food;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBAGFood extends ItemFood {
    /**
     * @param hungerHeal 恢复的饱食度
     * @param saturation 相对饱和度，这是Minecraft的机制，营养值 = 2*saturation*hungerHeal，营养值高的食物应该在饱食度高情况下吃
     * @param isWolfFood 能否喂给狼......这什么鬼*/
    public ItemBAGFood(int hungerHeal,float saturation,boolean isWolfFood){
        super(hungerHeal,saturation,isWolfFood);
        this.setCreativeTab(BAGCreativeTabs.ITEM_TAB);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        tooltip.add(I18n.format("tooltip.bloodandglory.food"));
    }
}
