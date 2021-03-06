package com.simibubi.create.content.contraptions.relays.advanced;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.foundation.render.instancing.InstanceContext;
import com.simibubi.create.foundation.render.instancing.InstancedModel;
import com.simibubi.create.foundation.render.instancing.RotatingData;
import com.simibubi.create.foundation.tileEntity.renderer.SmartTileEntityRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class SpeedControllerRenderer extends SmartTileEntityRenderer<SpeedControllerTileEntity> {

	public SpeedControllerRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	protected void renderSafe(SpeedControllerTileEntity tileEntityIn, float partialTicks, MatrixStack ms,
			IRenderTypeBuffer buffer, int light, int overlay) {
		super.renderSafe(tileEntityIn, partialTicks, ms, buffer, light, overlay);
//		addInstanceData(new InstanceContext.World<>(tileEntityIn));
	}

	public void addInstanceData(InstanceContext<SpeedControllerTileEntity> ctx) {
		KineticTileEntityRenderer.renderRotatingBuffer(ctx, getRotatedModel(ctx));
	}

	public void markForRebuild(InstanceContext<SpeedControllerTileEntity> ctx) {
		getRotatedModel(ctx).clearInstanceData();
	}

	private InstancedModel<RotatingData> getRotatedModel(InstanceContext<SpeedControllerTileEntity> ctx) {
		return ctx.getRotating().getModel(KineticTileEntityRenderer.KINETIC_TILE,
				KineticTileEntityRenderer.shaft(KineticTileEntityRenderer.getRotationAxisOf(ctx.te)));
	}

}
