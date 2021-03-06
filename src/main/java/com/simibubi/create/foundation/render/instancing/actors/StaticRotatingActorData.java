package com.simibubi.create.foundation.render.instancing.actors;

import com.simibubi.create.foundation.render.instancing.InstanceData;
import com.simibubi.create.foundation.render.instancing.VertexFormat;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.util.math.BlockPos;

import java.nio.ByteBuffer;

import static com.simibubi.create.foundation.render.instancing.VertexAttribute.*;

public class StaticRotatingActorData extends InstanceData {
    public static VertexFormat FORMAT = new VertexFormat(POSITION, FLOAT, NORMAL, VEC3, NORMAL);

    private float x;
    private float y;
    private float z;
    private float rotationOffset;
    private byte rotationAxisX;
    private byte rotationAxisY;
    private byte rotationAxisZ;
    private float localRotationX;
    private float localRotationY;
    private float localRotationZ;
    private byte rotationCenterX = 64;
    private byte rotationCenterY = 64;
    private byte rotationCenterZ = 64;

    public StaticRotatingActorData setPosition(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        return this;
    }

    public StaticRotatingActorData setRotationOffset(float rotationOffset) {
        this.rotationOffset = rotationOffset;
        return this;
    }

    public StaticRotatingActorData setRotationAxis(Vector3f axis) {
        setRotationAxis(axis.getX(), axis.getY(), axis.getZ());
        return this;
    }

    public StaticRotatingActorData setRotationAxis(float rotationAxisX, float rotationAxisY, float rotationAxisZ) {
        this.rotationAxisX = (byte) (rotationAxisX * 127);
        this.rotationAxisY = (byte) (rotationAxisY * 127);
        this.rotationAxisZ = (byte) (rotationAxisZ * 127);
        return this;
    }

    public StaticRotatingActorData setRotationCenter(Vector3f axis) {
        setRotationCenter(axis.getX(), axis.getY(), axis.getZ());
        return this;
    }

    public StaticRotatingActorData setRotationCenter(float rotationCenterX, float rotationCenterY, float rotationCenterZ) {
        this.rotationCenterX = (byte) (rotationCenterX * 127);
        this.rotationCenterY = (byte) (rotationCenterY * 127);
        this.rotationCenterZ = (byte) (rotationCenterZ * 127);
        return this;
    }

    public StaticRotatingActorData setLocalRotation(Vector3f axis) {
        setLocalRotation(axis.getX(), axis.getY(), axis.getZ());
        return this;
    }

    public StaticRotatingActorData setLocalRotation(float localRotationX, float localRotationY, float localRotationZ) {
        this.localRotationX = localRotationX;
        this.localRotationY = localRotationY;
        this.localRotationZ = localRotationZ;
        return this;
    }

    @Override
    public void write(ByteBuffer buf) {
        putVec3(buf, x, y, z);
        put(buf, rotationOffset);
        putVec3(buf, rotationAxisX, rotationAxisY, rotationAxisZ);
        putVec3(buf, localRotationX, localRotationY, localRotationZ);
        putVec3(buf, rotationCenterX, rotationCenterY, rotationCenterZ);

    }
}
