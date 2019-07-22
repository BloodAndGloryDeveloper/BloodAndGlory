package bloodandglory.common.entity.mobs;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityBandit extends EntityTameable implements IMob {

    private EntityAIAttackMelee meleeAI; //发呆
    private EntityAIWander wanderAI;     //走路
    private EntityAIWatchClosest watchAI;//看着你

    public EntityBandit(World worldIn){
        super(worldIn);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        //第一个参数是长宽，第二个是高度
        this.setSize(0.9F,1.95F);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (this.rand.nextInt(3) == 0){
            this.dropItem(Items.IRON_AXE,1);
        }
        super.dropLoot(wasRecentlyHit,lootingModifier,source);
    }

    @Override
    protected void initEntityAI() {
        this.meleeAI = new EntityAIAttackMelee(this,0.5F,true);
        this.wanderAI = new EntityAIWander(this,0.8F);
        this.watchAI = new EntityAIWatchClosest(this, EntityPlayer.class,16);

        //非攻击行为
        this.tasks.addTask(0,new EntityAISwimming(this));
        this.tasks.addTask(1,new EntityAIBreakDoor(this));
        this.tasks.addTask(2,this.meleeAI);
        this.tasks.addTask(3,this.wanderAI);
        this.tasks.addTask(3,this.watchAI);
        //攻击行为
        this.targetTasks.addTask(2,new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2,new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3,new EntityAINearestAttackableTarget<EntityMob>(this, EntityMob.class,true));
        //打家劫舍(笑)，只有最后才会打劫村民哦(不，你不是正宗劫匪)
        this.targetTasks.addTask(4,new EntityAITargetNonTamed<EntityVillager>(this, EntityVillager.class, true, new Predicate<EntityVillager>() {
            @Override
            public boolean apply(@Nullable EntityVillager input) {
                return true;
            }
        }));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(2.0D);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        EntityBandit entityBandit = new EntityBandit(world);
        UUID uuid = this.getOwnerId();

        if (uuid != null){
            entityBandit.setOwnerId(uuid);
        }

        return entityBandit;
    }

    public void setAngry(boolean angry)
    {
        byte b0 = ((Byte)this.dataManager.get(TAMED)).byteValue();

        if (angry)
        {
            this.dataManager.set(TAMED, Byte.valueOf((byte)(b0 | 2)));
        }
        else
        {
            this.dataManager.set(TAMED, Byte.valueOf((byte)(b0 & -3)));
        }
    }
}
