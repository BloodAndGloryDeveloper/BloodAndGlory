package bloodandglory.common.item.food;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemBAGFood extends ItemFood {
    /**
     * @param hungerHeal 恢复的饱食度
     * @param saturation 相对饱和度，这是Minecraft的机制，营养值 = 2*saturation*hungerHeal，营养值高的食物应该在饱食度高情况下吃
     * @param isWolfFood 能否喂给狼......这什么鬼*/
    public ItemBAGFood(int hungerHeal,float saturation,boolean isWolfFood){
        super(hungerHeal,saturation,isWolfFood);
        this.setCreativeTab(BAGCreativeTabs.ITEM_TAB);
    }
}
