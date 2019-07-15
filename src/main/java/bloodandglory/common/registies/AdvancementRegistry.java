package bloodandglory.common.registies;

import bloodandglory.common.advancement.ItemTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class AdvancementRegistry {
    public static final ItemTrigger ADVANCEMENT_HAS = CriteriaTriggers.register(new ItemTrigger());

    public static void preInit(){}
}
