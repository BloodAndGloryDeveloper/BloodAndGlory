package bloodandglory.common.advancement;

import bloodandglory.BloodAndGlory;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

import java.util.Map;
import java.util.Set;

public class ItemTrigger implements ICriterionTrigger<ItemTrigger.Instance> {
    public static ResourceLocation ID = BloodAndGlory.setPathIn("item_trigger");
    private final Map<PlayerAdvancements,ItemTrigger.Listeners> listences = Maps.newHashMap();

    @Override
    public ResourceLocation getId() {return ID;}

    @Override
    public void addListener(PlayerAdvancements playerAdvancements,Listener<ItemTrigger.Instance> listener){
        ItemTrigger.Listeners listeners = this.listences.computeIfAbsent(playerAdvancements, Listeners::new);
        listeners.add(listener);
    }
    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<ItemTrigger.Instance> listener){
        ItemTrigger.Listeners listeners = this.listences.get(playerAdvancementsIn);
        if (listeners != null){
            listeners.remove(listener);
            if (listeners.isEmpty()){
                this.listences.remove(playerAdvancementsIn);
            }
        }
    }
    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn){
        this.listences.remove(playerAdvancementsIn);
    }
    @Override
    public ItemTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context){
        return new ItemTrigger.Instance();
    }

    public void trigger(EntityPlayerMP player){
        ItemTrigger.Listeners listeners = this.listences.get(player.getAdvancements());
        if (listeners != null){
            listeners.trigger();
        }
    }

    public static class Instance extends AbstractCriterionInstance{
        public Instance() {super(ItemTrigger.ID);}
    }


    static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<ItemTrigger.Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn){
            this.playerAdvancements = playerAdvancementsIn;
        }
        public boolean isEmpty(){
            return this.listeners.isEmpty();
        }
        public void add(Listener<ItemTrigger.Instance> listener){
            this.listeners.add(listener);
        }
        public void remove(Listener<ItemTrigger.Instance> listener){
            this.listeners.remove(listener);
        }

        public void trigger() {
            for (Listener<ItemTrigger.Instance> listener : Lists.newArrayList(listeners)){
                listener.grantCriterion(this.playerAdvancements);
            }
        }
    }
}
