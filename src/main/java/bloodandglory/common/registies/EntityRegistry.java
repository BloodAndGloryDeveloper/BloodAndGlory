package bloodandglory.common.registies;

import bloodandglory.BloodAndGlory;
import bloodandglory.ModInfo;
import bloodandglory.common.entity.mobs.EntityBandit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;


public class EntityRegistry {

    private static class registryEntityHelper{
        private final IForgeRegistry<EntityEntry> registry;

        private int id = 0;

        registryEntityHelper(IForgeRegistry<EntityEntry> registry){
            this.registry = registry;
        }

        private static String toString(ResourceLocation location){
            return location.getResourceDomain() + "." + location.getResourcePath();
        }

        final <T extends Entity> EntityEntryBuilder<T> builder(ResourceLocation registryName, Class<T> entity, Function<World, T> factory) {
            return EntityEntryBuilder.<T>create().id(registryName, id++).name(toString(registryName)).entity(entity).factory(factory);
        }

        final <T extends Entity> void registerEntity(ResourceLocation registryName, Class<T> entity, Function<World, T> factory, int backgroundEggColour, int foregroundEggColour) {
            registerEntity(registryName, entity, factory, backgroundEggColour, foregroundEggColour, 80, 3, true);
        }

        final <T extends Entity> void registerEntity(ResourceLocation registryName, Class<T> entity, Function<World, T> factory, int backgroundEggColour, int foregroundEggColour, int trackingRange, int updateInterval, boolean sendVelocityUpdates) {
            registry.register(builder(registryName, entity, factory).tracker(trackingRange, updateInterval, sendVelocityUpdates).egg(backgroundEggColour, foregroundEggColour).build());
        }

        final <T extends Entity> void registerEntity(ResourceLocation registryName, Class<T> entity, Function<World, T> factory) {
            registerEntity(registryName, entity, factory, 80, 3, true);
        }

        final <T extends Entity> void registerEntity(ResourceLocation registryName, Class<T> entity, Function<World, T> factory, int trackingRange, int updateInterval, boolean sendVelocityUpdates) {
            registry.register(builder(registryName, entity, factory).tracker(trackingRange, updateInterval, sendVelocityUpdates).build());
        }
    }

    @SubscribeEvent
    public static void registryentity(RegistryEvent.Register<EntityEntry> event){
        registryEntityHelper helper = new registryEntityHelper(event.getRegistry());

        helper.registerEntity(BloodAndGlory.setPathIn("bandit"),EntityBandit.class,EntityBandit::new,0xA0522D,0xFFDEAD);
        net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn(EntityBandit.class,100,5,9, EnumCreatureType.CREATURE, Biome.getBiome(1));

    }
}
