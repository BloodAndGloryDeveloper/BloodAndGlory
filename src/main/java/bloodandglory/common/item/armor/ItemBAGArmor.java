package bloodandglory.common.item.armor;

import bloodandglory.ModInfo;
import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemBAGArmor extends ItemArmor implements ISpecialArmor {
    protected final String armorTexture1,armorTexture2;
    protected final String armorName;

    /**
     * @param material   护甲的材质
     * @param renderType 填0
     * @param slot       护甲穿在实体的部位
     */
    public ItemBAGArmor(ArmorMaterial material, int renderType, EntityEquipmentSlot slot,String armorName) {
        super(material, renderType, slot);
        this.setCreativeTab(BAGCreativeTabs.ITEM_TAB);

        this.armorName = armorName;
        this.armorTexture1 = ModInfo.ASSTES + "textures/armors/" + armorName + "_layer_1.png";
        this.armorTexture2 = ModInfo.ASSTES + "textures/armors/" + armorName + "_layer_2.png";
    }

    /**
     * 根据不同身体部位来返回渲染材质
     */
    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if (slot == EntityEquipmentSlot.LEGS){
            return this.armorTexture2;
        }else {
            return this.armorTexture1;
        }
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0,1.0,100);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {

    }
}
