package bloodandglory.common;

import bloodandglory.common.registies.ItemRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy{
    public void preInit(FMLPreInitializationEvent event){

    }
    public void init(FMLInitializationEvent event){
        ItemRegistry.addSmelting();
    }
}
