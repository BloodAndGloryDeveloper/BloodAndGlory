package bloodandglory.common.tile;

import net.minecraft.tileentity.TileEntityFurnace;

/**
 * 为什么BlockBAGFurnace我继承Block类而TileEntityBAGFurnace去继承TileEntityFurnace呢？
 * 其实是因为我不想重写熔炉界面了了
 */
public class TileEntityBAGFurnace extends TileEntityFurnace {
    public int furnaceBurnTime;
    public int furnaceCookTime;
    public int currentItemBurnTime;
    public String customName;

    public TileEntityBAGFurnace(){
        super();
    }

    public boolean hasCustomName(){

    }
}
