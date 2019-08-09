package bloodandglory.common.event;

import bloodandglory.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
public class OnBlockLeftClicked {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLeftClicked(PlayerInteractEvent.LeftClickBlock event){
        Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
        EntityPlayer player = event.getEntityPlayer();


        String translateKey = "text." + ModInfo.ID_NAME;
        if (event.getWorld().isRemote){
            return;
        }

        //fixme:这个太长了，就像猫从厕所扒下来的手纸一样长
        if (block == Blocks.COAL_ORE){
            player.sendMessage(new TextComponentTranslation(I18n.format(translateKey + "block.coal_ore")));
        }else if (block == Blocks.REDSTONE_ORE || block == Blocks.LIT_REDSTONE_ORE){
            player.sendMessage(new TextComponentTranslation(I18n.format(translateKey + "block.redstone_ore")));
        }else if (block == Blocks.IRON_ORE ){
            player.sendMessage(new TextComponentTranslation(I18n.format(translateKey + "block.iron_ore")));
        }else if (block == Blocks.GOLD_ORE){
            player.sendMessage(new TextComponentTranslation(I18n.format(translateKey + "block.gold.ore")));
        }else if (block == Blocks.EMERALD_ORE){
            player.sendMessage(new TextComponentTranslation(I18n.format(translateKey + "block.emerald_ore")));
        } else if (block == Blocks.DIAMOND_ORE){
            player.sendMessage(new TextComponentTranslation(I18n.format(translateKey + "block.diamond_ore")));
        }
    }
}
