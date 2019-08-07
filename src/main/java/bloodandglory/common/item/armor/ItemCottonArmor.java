package bloodandglory.common.item.armor;

import bloodandglory.common.item.ItemBAGMaterial;
import bloodandglory.common.item.misc.ItemBAGArrow;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemCottonArmor extends ItemBAGArmor {
    public ItemCottonArmor(EntityEquipmentSlot slot){
        super(ItemBAGMaterial.ARMOR_COTTON,0,slot,"cotton");
    }


}
