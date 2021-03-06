package com.simibubi.create.content.contraptions.components.motor;

import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.foundation.render.instancing.InstanceContext;
import com.simibubi.create.foundation.render.instancing.InstancedModel;
import com.simibubi.create.foundation.render.instancing.RotatingData;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class CreativeMotorRenderer extends KineticTileEntityRenderer {

	public CreativeMotorRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	protected InstancedModel<RotatingData> getRotatedModel(InstanceContext<? extends KineticTileEntity> ctx) {
		return AllBlockPartials.SHAFT_HALF.renderOnDirectionalSouthRotating(ctx, ctx.te.getBlockState());
	}

}
