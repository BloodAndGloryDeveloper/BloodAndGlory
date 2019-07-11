package bloodandglory.common.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAlcohol extends ItemBAGFood{
    public ItemAlcohol(){
        super(0,0.0F,false);
        //永远可食
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,20,1));
        player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,300,2));
    }
}
