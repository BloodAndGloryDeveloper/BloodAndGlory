package bloodandglory.common.registies;

import bloodandglory.ModInfo;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry {
    public static void init(){
        /*registryTileEntity(TileEntityBAGFurnace.class,"industrial_boiler");*/
    }

    private static void registryTileEntity(Class<? extends TileEntity>cls,String baseName){
        GameRegistry.registerTileEntity(cls,new ResourceLocation(ModInfo.MOD_ID,baseName));
    }
}
