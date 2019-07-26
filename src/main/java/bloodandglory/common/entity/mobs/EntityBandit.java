package bloodandglory.common.entity.mobs;

import bloodandglory.common.entity.EntityBAGTameble;
import com.google.common.base.Predicate;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class EntityBandit extends EntityBAGTameble {

    private EntityAIAttackMelee meleeAI; //发呆
    private EntityAIWander wanderAI;     //走路
    private EntityAIWatchClosest watchAI;//看着你

    public EntityBandit(World worldIn){
        super(worldIn);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        //第一个参数是长宽，第二个是高度
        this.setSize(0.9F,1.95F);
        this.setTamed(false);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
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

        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, false, false, new Predicate<EntityLiving>() {
            @Override
            public boolean apply(@Nullable EntityLiving input) {
                return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input) && !(input instanceof EntityEnderman)&&!(input instanceof EntityBandit);
            }
        }));

    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.56D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }
    //=======生成生物代码=================

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData data = super.onInitialSpawn(difficulty,livingdata);

        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setDropChance(EntityEquipmentSlot.MAINHAND,0.3F);

        return data;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        int i = rand.nextInt(3);//三种可能
        switch (i){
            case 0:
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,new ItemStack(Items.IRON_AXE));
                break;
            case 1:
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,new ItemStack(Items.IRON_SWORD));
                break;
            case 2:
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,new ItemStack(Items.BOW));
                break;
            default:
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,new ItemStack(Items.SHIELD));
        }

    }

    //=======生成代码结束==============

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }
}
