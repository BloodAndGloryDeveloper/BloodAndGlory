package bloodandglory;

import bloodandglory.common.CommonProxy;
import bloodandglory.common.registies.BlockRegistry;
import bloodandglory.common.registies.ItemRegistry;
import bloodandglory.common.registies.RecipeRegistry;
import bloodandglory.common.registies.Registry;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.advancements.critereon.ItemPredicates;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import scala.collection.parallel.ParIterableLike;

@Mod(modid = ModInfo.MOD_ID,name = ModInfo.NAME,version = ModInfo.VERSION)
public class BloodAndGlory {
    @Instance(value = ModInfo.MOD_ID)
    public static BloodAndGlory bloodAndGlory;

    @SidedProxy(clientSide = ModInfo.CLIENTSIDE,serverSide = ModInfo.SERVERSIDE)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Registry.instance.preInit();

        proxy.preInit(event);
        MinecraftForge.EVENT_BUS.register(ItemRegistry.class);
        MinecraftForge.EVENT_BUS.register(BlockRegistry.class);
        MinecraftForge.EVENT_BUS.register(RecipeRegistry.class);
    }

    public static ResourceLocation setPathIn(String pathIn){
        return new ResourceLocation(ModInfo.MOD_ID,pathIn);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);

        Registry.instance.init();
    }
}
