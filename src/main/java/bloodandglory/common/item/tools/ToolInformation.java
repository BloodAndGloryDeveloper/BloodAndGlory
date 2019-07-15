package bloodandglory.common.item.tools;

import bloodandglory.common.item.ItemBAGMaterial;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.*;

import java.util.List;

public class ToolInformation {
    private ToolInformation(){}

    public static final String KEY_START =  "tooltip.bloodandglory.";

    public static void addToolInformation(List<String> tootip,Object itemTool){
        if (itemTool instanceof ItemAxe){
            tootip.add(I18n.format(KEY_START + "axe" + "\r\n"));
        }else if (itemTool instanceof ItemPickaxe){
            tootip.add(I18n.format(KEY_START + "pickaxe" + "\r\n"));
        }else if (itemTool instanceof ItemSpade){
            tootip.add(I18n.format(KEY_START + "shovel"));
        }else if (itemTool instanceof ItemHoe){
            tootip.add(I18n.format(KEY_START + "hoe"));
        }else if (itemTool instanceof ItemSword){
            tootip.add(I18n.format(KEY_START + "sword"));
        }
    }
}
