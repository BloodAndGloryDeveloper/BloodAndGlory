package bloodandglory.common.item.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRise extends ItemBAGFood{
    public ItemRise(int hungerHeal,float saturation,boolean isWolfFood){
        super(hungerHeal, saturation, isWolfFood);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving){
        super.onItemUseFinish(stack, world, entityLiving);
        return new ItemStack(Items.BOW);
    }
}
