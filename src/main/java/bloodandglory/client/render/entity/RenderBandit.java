package bloodandglory.client.render.entity;

import bloodandglory.client.model.ModelBandit;
import bloodandglory.common.entity.mobs.EntityBandit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderBandit extends RenderLiving<EntityBandit> {
    private final ResourceLocation texture = new ResourceLocation("bloodandglory:textures/entities/bandit.png");

    public RenderBandit(RenderManager manager){
        super(manager,new ModelBandit(),0.7F);
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
