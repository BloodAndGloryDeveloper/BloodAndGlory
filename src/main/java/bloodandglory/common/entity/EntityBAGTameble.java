package bloodandglory.common.entity;

import bloodandglory.common.entity.mobs.EntityBandit;
import com.google.common.base.Optional;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityBAGTameble extends EntityMob implements IEntityOwnable {
    private static final DataParameter<Byte> TAMED = EntityDataManager.<Byte>createKey(EntityBandit.class, DataSerializers.BYTE);
    private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityTameable.class, DataSerializers.OPTIONAL_UNIQUE_ID);

    protected EntityBAGTameble(World worldIn){
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(TAMED,Byte.valueOf((byte) 0));
        this.dataManager.register(OWNER_UNIQUE_ID,Optional.absent());
    }

    //todo
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        if (this.getOwnerId() == null){
            compound.setString("OwnerUUID","");
        }else {
            compound.setString("OwnerUUID",this.getOwnerId().toString());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        String s;

        if (compound.hasKey("OwnerUUID",8)){
            s = compound.getString("OwnerUUID");
        }else {
            String s1 = compound.getString("Owner");
            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
        }
        if (!s.isEmpty()){
            try {
                this.setOwnerId(UUID.fromString(s));
                this.setTamed(true);
            }catch (Throwable ex){
                this.setTamed(false);
            }
        }
    }

    @Nullable
    @Override
    public UUID getOwnerId() {
        return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
    }
    public void setOwnerId(@Nullable UUID playerUUID){
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(playerUUID));
    }

    @Nullable
    @Override
    public EntityLivingBase getOwner() {
        try {
            UUID uuid = this.getOwnerId();
            return uuid == null ? null : this.world.getPlayerEntityByUUID(uuid);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    /*以下就是和雇佣兵愉快的交♂易时间了*/
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        //获取玩家手上的物品
        ItemStack itemStack = player.getHeldItem(hand);

        if (itemStack.getItem() == Items.EMERALD){
            //玩家手中数量物品-1
            if (!player.capabilities.isCreativeMode){
                itemStack.shrink(1);
            }
            if (!this.world.isRemote){
                this.playTameEffect(true);
                this.setTamedBy(player);
            }

        }
        return false;
    }

    public void setTamedBy(EntityPlayer player){
        this.setTamed(true);
        this.setOwnerId(player.getUniqueID());

    }

    public void setTamed(boolean tamed){
        byte b0 = ((Byte)this.dataManager.get(TAMED)).byteValue();
        if (tamed){
            this.dataManager.set(TAMED,Byte.valueOf((byte)(b0 | 4)));
        }else {
            this.dataManager.set(TAMED, Byte.valueOf((byte)(b0 & -5)));
        }
    }


    protected void playTameEffect(boolean play)
    {
        EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

        if (!play) {
            enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
        }

        for (int i = 0; i < 7; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }
}
