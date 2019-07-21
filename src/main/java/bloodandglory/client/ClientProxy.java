package bloodandglory.client;

import bloodandglory.client.render.entity.RenderBandit;
import bloodandglory.common.CommonProxy;
import bloodandglory.common.entity.mobs.EntityBandit;
import bloodandglory.common.registies.ItemRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.HashMap;
import java.util.Map;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        RenderingRegistry.registerEntityRenderingHandler(EntityBandit.class, RenderBandit::new);
    }
    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
    }

    @Override
    public void registerDefaultItemRenderer(Item item) {
        Map<Integer, ResourceLocation> map = this.getItemModelMap(item);
        for (Map.Entry<Integer, ResourceLocation> entry : map.entrySet()){
            ModelLoader.setCustomModelResourceLocation(item,entry.getKey(),(ModelResourceLocation) entry.getValue());
        }
        if (item instanceof ItemRegistry.ICustomMeshCallback){
            ModelLoader.setCustomMeshDefinition(item,((ItemRegistry.ICustomMeshCallback) item).getMeshDefinition());
        }
    }

    @Override
    public Map<Integer, ResourceLocation> getItemModelMap(Item item){
        Map<Integer, ResourceLocation> map = new HashMap<>();
        if (item instanceof ItemRegistry.IMultipleItemModelDefinition){
            for (Map.Entry<Integer, ResourceLocation> model : ((ItemRegistry.IMultipleItemModelDefinition) item).getModels().entrySet()){
                map.put(model.getKey(), new ModelResourceLocation(model.getValue(), "inventory"));
            }
        }else {
            map.put(0, new ModelResourceLocation(item.getRegistryName().toString(),"inventory"));
        }
        return map;
    }
}
