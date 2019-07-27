package bloodandglory.common.registies;

import bloodandglory.common.item.misc.ItemMisc;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class RecipeRegistry {
    private static void SmeltingRegistry(){
        GameRegistry.addSmelting(new ItemStack(BlockRegistry.MITHRIL_ORE), ItemMisc.EnumItemMisc.MITHRIL.create(1),2.0F);
        GameRegistry.addSmelting(new ItemStack(ItemRegistry.RAW_RICE),new ItemStack(ItemRegistry.RICE),1.5F);
        GameRegistry.addSmelting(new ItemStack(Items.EGG), new ItemStack(ItemRegistry.COOKED_EGG),1F);
    }

    @SubscribeEvent
    public static void registryRecipes(RegistryEvent.Register<IRecipe> event){
        IForgeRegistry<IRecipe> registry = event.getRegistry();

        SmeltingRegistry();
    }
}
