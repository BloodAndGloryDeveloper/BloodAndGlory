package bloodandglory.common.block.plant;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockBAGCrops extends BlockCrops {
    private final Item seed,crop;

    public BlockBAGCrops(Item seed,Item crop){
        super();
        this.seed = seed;
        this.crop = crop;
    }

    @Override
    protected Item getSeed() {
        return this.seed;
    }

    @Override
    protected Item getCrop() {
        return this.crop;
    }
}
