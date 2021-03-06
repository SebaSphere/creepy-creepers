package com.mcmoddev.creepycreepers.client.rendering;

import com.mcmoddev.creepycreepers.CreepyCreepers;
import com.mcmoddev.creepycreepers.client.models.GhostlyCreeperModel;
import com.mcmoddev.creepycreepers.common.entities.GhostlyCreeperEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class RenderGhostlyCreeper extends MobRenderer<GhostlyCreeperEntity, GhostlyCreeperModel> {

	private static final ResourceLocation resourceLocation = new ResourceLocation(CreepyCreepers.MOD_ID,
		"textures/entity/ghostly_creeper.png");

	public RenderGhostlyCreeper(EntityRendererManager rendererManager) {
		super(rendererManager, new GhostlyCreeperModel(), 0.4F);
		shadowSize = 0;
	}

	@Override
	protected void preRenderCallback(GhostlyCreeperEntity entity, float partialTickTime) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		float f = entity.getCreeperFlashIntensity(partialTickTime);
		float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		GlStateManager.scalef(f2, f3, f2);
	}

	@Override
	protected boolean canRenderName(GhostlyCreeperEntity entity) {
		return entity.hasCustomName();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(@Nonnull GhostlyCreeperEntity entity) {
		return resourceLocation;
	}
}
