package bloodandglory.common.item.armor;

import bloodandglory.common.item.ItemBAGMaterial;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemMithrilArmor extends ItemBAGArmor{
    public ItemMithrilArmor(EntityEquipmentSlot slot){
        super(ItemBAGMaterial.ARMOR_MITHRIL,0,slot,"mithril");
    }
}
