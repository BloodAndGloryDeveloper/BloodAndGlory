package bloodandglory.common;

import bloodandglory.common.world.gen.OreGenEventHandler;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;
import java.util.Map;

public class CommonProxy{
    public void preInit(FMLPreInitializationEvent event){

    }
    public void init(FMLInitializationEvent event){
        MinecraftForge.ORE_GEN_BUS.register(OreGenEventHandler.class);
    }
    public void registerDefaultItemRenderer(Item item){
    }

    public Map<Integer, ResourceLocation> getItemModelMap(Item item){
        return Collections.emptyMap();
    }
}
