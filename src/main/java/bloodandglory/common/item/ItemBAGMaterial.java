package bloodandglory.common.item;

import bloodandglory.ModInfo;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBAGMaterial {
    public static final ToolMaterial TOOL_MITHRIL = EnumHelper.addToolMaterial("mithril",3,1009,10.0F,5.0F,30);
    public static final ToolMaterial TOOL_BRONZE = EnumHelper.addToolMaterial("bronze",2,239,9.0F,2.0F,8);
    public static final ToolMaterial TOOL_STEEL = EnumHelper.addToolMaterial("steel",2,500,7.0F,4.0F,17);
    public static final ToolMaterial TOOL_STEELMAKING = EnumHelper.addToolMaterial("steelmaking",3,700,8.0F,5.0F,20);

    public static final String TEXTURE = ModInfo.MOD_ID + ":";
    public static final ArmorMaterial ARMOR_MITHRIL = EnumHelper.addArmorMaterial("mithril", TEXTURE + "mithril",20,
            new int[]{2,10,5,3},21, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,2);
    public static final ArmorMaterial ARMOR_BRONZE = EnumHelper.addArmorMaterial("bronze",TEXTURE + "bronze",14,
            new int[]{2,7,4,5},10,SoundEvents.ITEM_ARMOR_EQUIP_IRON,2);
    public static final ArmorMaterial ARMOR_COTTON = EnumHelper.addArmorMaterial("cotton",TEXTURE + "cotton",10,
            new int[]{2,2,2,2},2,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,4);
    public static final ArmorMaterial ARMOR_PLATE_MAIL = EnumHelper.addArmorMaterial("plate_mail",TEXTURE + "plate_mail",23,
            new int[]{5,5,5,5},17,SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,3);
}