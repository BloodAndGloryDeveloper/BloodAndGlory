package bloodandglory.common.block;

import bloodandglory.client.tab.BAGCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBAG extends Block{
    public BlockBAG(Material material){
        super(material);
        this.setCreativeTab(BAGCreativeTabs.BLOCK_TAB);
    }
}
