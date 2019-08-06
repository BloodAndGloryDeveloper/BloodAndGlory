package bloodandglory.common.item.armor;

import bloodandglory.common.item.ItemBAGMaterial;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemPlateMailArmor extends ItemBAGArmor{
    public ItemPlateMailArmor(EntityEquipmentSlot slot){
        super(ItemBAGMaterial.ARMOR_PLATE_MAIL,0,slot,"plate_mail");
    }
}
