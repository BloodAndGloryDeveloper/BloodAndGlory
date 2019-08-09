package bloodandglory.client.render;

import bloodandglory.ModInfo;
import bloodandglory.common.registies.ItemRegistry;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
public class ItemBAGColor implements IItemColor {
    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        return 0;
    }

    @SubscribeEvent
    public static void registryColors(ColorHandlerEvent.Item event){
        event.getItemColors().registerItemColorHandler(new IItemColor() {
            @Override
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                return tintIndex > 0 ? -1 : ((ItemArmor)stack.getItem()).getColor(stack);
            }
        }, ItemRegistry.COTTON_HELMET,ItemRegistry.COTTON_HELMET,ItemRegistry.COTTON_LEGGINGS,ItemRegistry.COTTON_BOOTS);
    }
}
