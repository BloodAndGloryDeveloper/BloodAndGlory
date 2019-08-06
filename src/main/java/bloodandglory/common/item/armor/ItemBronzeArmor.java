package bloodandglory.common.item.armor;

import bloodandglory.common.item.ItemBAGMaterial;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemBronzeArmor extends ItemBAGArmor {
    public ItemBronzeArmor(EntityEquipmentSlot slot){
        super(ItemBAGMaterial.ARMOR_BRONZE,0,slot,"bronze");
    }
}
