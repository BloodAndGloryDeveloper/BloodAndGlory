package bloodandglory.common;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;
import java.util.Map;

public class CommonProxy{
    public void preInit(FMLPreInitializationEvent event){

    }
    public void init(FMLInitializationEvent event){

    }
    public void registerDefaultItemRenderer(Item item){
    }

    public Map<Integer, ResourceLocation> getItemModelMap(Item item){
        return Collections.emptyMap();
    }
}
