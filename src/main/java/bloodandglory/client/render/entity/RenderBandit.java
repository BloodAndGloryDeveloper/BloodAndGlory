package bloodandglory.client.render.entity;

import bloodandglory.client.render.model.entity.ModelBandit;
import bloodandglory.common.entity.mobs.EntityBandit;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderBandit extends RenderBiped<EntityBandit> {
    private final ResourceLocation texture = new ResourceLocation("bloodandglory:textures/entities/bandit.png");

    public RenderBandit(RenderManager manager){
        super(manager,new ModelBandit(),0.5F);
        LayerBipedArmor layerBipedArmor = new LayerBipedArmor(this){
            @Override
            protected void initArmor() {
                this.modelLeggings = new ModelBandit(0.5F,true);
                this.modelArmor = new ModelBandit(1.0F,true);
            }
        };
        this.addLayer(layerBipedArmor);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBandit entity) {
        return texture;
    }


    @Override
    public void doRender(EntityBandit entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
