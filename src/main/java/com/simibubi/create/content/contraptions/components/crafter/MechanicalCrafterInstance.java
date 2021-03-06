package com.simibubi.create.content.contraptions.components.crafter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance;
import com.simibubi.create.foundation.render.InstancedTileRenderer;
import com.simibubi.create.foundation.render.instancing.InstancedModel;
import com.simibubi.create.foundation.render.instancing.InstancedTileRenderRegistry;
import com.simibubi.create.foundation.render.instancing.RotatingData;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.MatrixStacker;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Supplier;

public class MechanicalCrafterInstance extends SingleRotatingInstance {
    public static void register(TileEntityType<? extends KineticTileEntity> type) {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () ->
                InstancedTileRenderRegistry.instance.register(type, MechanicalCrafterInstance::new));
    }

    public MechanicalCrafterInstance(InstancedTileRenderer modelManager, KineticTileEntity tile) {
        super(modelManager, tile);
    }

    @Override
    protected InstancedModel<RotatingData> getModel() {
        Direction facing = lastState.get(MechanicalCrafterBlock.HORIZONTAL_FACING);

        Supplier<MatrixStack> ms = () -> {
            MatrixStack stack = new MatrixStack();
            MatrixStacker.of(stack)
                         .centre()
                         .rotateZ(90)
                         .rotateY(AngleHelper.horizontalAngle(facing))
                         .rotateX(AngleHelper.verticalAngle(facing))
                         .unCentre();
            return stack;
        };
        return rotatingMaterial().getModel(AllBlockPartials.SHAFTLESS_COGWHEEL, lastState, facing, ms);
    }
}
