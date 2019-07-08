package bloodandglory;

import bloodandglory.common.CommonProxy;
import bloodandglory.common.registies.BlockRegistry;
import bloodandglory.common.registies.ItemRegistry;
import bloodandglory.common.registies.Registry;
import jdk.nashorn.internal.ir.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MOD_ID,name = ModInfo.NAME,version = ModInfo.VERSION)
public class BloodAndGlory {
    @Instance(value = ModInfo.MOD_ID)
    public BloodAndGlory bloodAndGlory;

    @SidedProxy(clientSide = ModInfo.CLIENTSIDE,serverSide = ModInfo.SERVERSIDE)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Registry.instance.preInit();

        proxy.preInit(event);
        MinecraftForge.EVENT_BUS.register(ItemRegistry.class);
        MinecraftForge.EVENT_BUS.register(BlockRegistry.class);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }
}
