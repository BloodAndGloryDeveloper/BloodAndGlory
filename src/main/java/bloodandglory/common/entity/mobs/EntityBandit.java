package bloodandglory.common.entity.mobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBandit extends EntityMob {
    public EntityBandit(World worldIn){
        super(worldIn);
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


}
