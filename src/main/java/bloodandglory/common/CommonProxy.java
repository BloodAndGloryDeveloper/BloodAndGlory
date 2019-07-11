package bloodandglory.common;

import bloodandglory.common.registies.ItemRegistry;
import bloodandglory.common.world.gen.OreGenEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy{
    public void preInit(FMLPreInitializationEvent event){

    }
    public void init(FMLInitializationEvent event){
        ItemRegistry.addSmelting();

        MinecraftForge.ORE_GEN_BUS.register(OreGenEventHandler.class);
    }
}
