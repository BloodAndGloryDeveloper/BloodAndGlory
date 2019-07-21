package bloodandglory.common.registies;

import bloodandglory.BloodAndGlory;
import bloodandglory.ModInfo;
import bloodandglory.common.entity.mobs.EntityBandit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;


public class EntityRegistry {
    public static void preInit(){
        registryEntity(EntityBandit.class,"bandit",0xA0522D,0xFFDEAD);
    }
    private static int id = 0;

    /**
     * @param entityClass 传入的实体类
     * @param name 实体名字，虽然很多Mod采用大写驼峰式，但为了日后指令输入方便采用全部小写
     * @param tranckingRange 实体的跟踪范围，一般指定为64，超出这个范围就不再更新实体
     * @param updateFrequency 实体更新频率，一般指定为3，每过指定的gametick就更新一次
     * @param velocityUpdates 决定是否同步更新，静止实体或可以手动更新的实体为false，一般为true
     * */
    private static void registryEntity(Class<? extends Entity> entityClass,String name,int tranckingRange,int updateFrequency,boolean velocityUpdates){
        net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.MOD_ID,name),entityClass,"bloodandglory."+name,id, BloodAndGlory.bloodAndGlory,tranckingRange,updateFrequency,velocityUpdates);
        id++;
    }
    private static void registryEntity(Class<? extends Entity> entityClass,String name){
        net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.MOD_ID,name),entityClass,"bloodandglory."+name,id,BloodAndGlory.bloodAndGlory,64,3,true);
        id++;
    }
    private static void registerEntity(Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor, int trackingRange, int updateFrequency, boolean velocityUpdates) {
        registryEntity(entityClass,name,trackingRange,updateFrequency,velocityUpdates);
        net.minecraftforge.fml.common.registry.EntityRegistry.registerEgg(new ResourceLocation(ModInfo.MOD_ID,name),eggBackgroundColor,eggForegroundColor);
        id++;
    }
    private static void registryEntity(Class<? extends EntityLiving> entityClass,String name,int eggBackgroundColor,int eggForegroundColor){
        registryEntity(entityClass, name);
        net.minecraftforge.fml.common.registry.EntityRegistry.registerEgg(new ResourceLocation(ModInfo.MOD_ID,name),eggBackgroundColor,eggForegroundColor);
        id++;
    }

}
