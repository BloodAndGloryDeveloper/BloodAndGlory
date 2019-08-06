package bloodandglory.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public class EntitySpear extends EntityThrowable {
    public EntitySpear(World worldIn){
        super(worldIn);
    }
    public EntitySpear(World worldIn, EntityLivingBase throwerIn){
        super(worldIn,throwerIn);
    }
    public EntitySpear(World worldIn, double x, double y, double z){
        super(worldIn, x, y, z);
    }

    //这个方法是投掷物击中实体或方块时的行为
    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null){
            int i = 0;
            if (result.entityHit instanceof EntityBlaze){
                i = 3;
            }

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this,this.getThrower()),(float)i);
        }
        if (!this.world.isRemote){
            this.world.setEntityState(this,(byte)3);
            this.setDead();
        }
    }
}

