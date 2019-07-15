package bloodandglory.common.item.misc;

import bloodandglory.common.item.food.ItemBAGFood;
import com.sun.istack.internal.NotNull;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemAlcohol extends ItemBAGFood {
    public ItemAlcohol(){
        super(0,0,false);
        this.setMaxStackSize(1);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player){
        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,1000,0));
        player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,3000,2));
    }
    @Override
    public EnumAction getItemUseAction(ItemStack stack){return EnumAction.DRINK;}

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @NotNull World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        tooltip.add(I18n.format("tooltip.bloodandglory.alcohol"));
    }

}
