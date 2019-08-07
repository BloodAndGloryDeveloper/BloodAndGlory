package bloodandglory.common.item.tools;

import bloodandglory.client.tab.BAGCreativeTabs;
import bloodandglory.common.entity.projectile.EntitySpear;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemSpear extends Item {
    public ItemSpear(){
        super();
        this.setCreativeTab(BAGCreativeTabs.TOOL_TAB);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        //播放射箭音效
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        //获取玩家手里的东西
        ItemStack item = playerIn.getHeldItem(handIn);
        //播放矛扔出去的声音
        worldIn.playSound(null,playerIn.posX,playerIn.posY,playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL,0.5F,0.4F /  0.4F + 0.8F);
        if (!playerIn.capabilities.isCreativeMode){
            item.shrink(1);
        }
        if (!worldIn.isRemote){
            //雪球是测试用，如果你看到的是0.1.0以前版本的代码，请忽略它
            EntityTippedArrow spear = new EntityTippedArrow(worldIn,playerIn);
            spear.shoot(playerIn,playerIn.rotationPitch,playerIn.rotationYaw,0.0F,1.5F,1.0F);
            worldIn.spawnEntity(spear);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS,item);
    }


}
