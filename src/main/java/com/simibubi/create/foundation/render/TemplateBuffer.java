package com.simibubi.create.foundation.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.datafixers.util.Pair;
import com.simibubi.create.foundation.render.gl.GlBuffer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.lwjgl.opengl.GL15;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TemplateBuffer {
    protected ByteBuffer template;
    protected int formatSize;
    protected int vertexCount;

    public TemplateBuffer(BufferBuilder buf) {
        Pair<BufferBuilder.DrawState, ByteBuffer> state = buf.popData();
        ByteBuffer rendered = state.getSecond();
        rendered.order(ByteOrder.nativeOrder()); // Vanilla bug, endianness does not carry over into sliced buffers

        formatSize = buf.getVertexFormat()
                        .getSize();
        vertexCount = state.getFirst().getCount();
        int size = vertexCount * formatSize;

        template = ByteBuffer.allocate(size);
        template.order(rendered.order());
        ((Buffer)template).limit(((Buffer)rendered).limit());
        template.put(rendered);
        ((Buffer)template).rewind();
    }

    protected final GlBuffer createEBO(){
        GlBuffer ebo = new GlBuffer();

        int indicesSize = vertexCount * VertexFormatElement.Type.USHORT.getSize();

        ebo.bind(GL15.GL_ELEMENT_ARRAY_BUFFER);

        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesSize, GL15.GL_STATIC_DRAW);

        ByteBuffer indices = GL15.glMapBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, GL15.GL_WRITE_ONLY);

        for (int i = 0; i < vertexCount; i++) {
            indices.putShort((short) i);
        }
        indices.rewind();

        GL15.glUnmapBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER);

        ebo.unbind(GL15.GL_ELEMENT_ARRAY_BUFFER);

        return ebo;
    }

    public boolean isEmpty() {
        return ((Buffer) template).limit() == 0;
    }

    protected int vertexCount(ByteBuffer buffer) {
        return ((Buffer)buffer).limit() / formatSize;
    }

    protected int getBufferPosition(int vertexIndex) {
        return vertexIndex * formatSize;
    }

    protected float getX(ByteBuffer buffer, int index) {
        return buffer.getFloat(getBufferPosition(index));
    }

    protected float getY(ByteBuffer buffer, int index) {
        return buffer.getFloat(getBufferPosition(index) + 4);
    }

    protected float getZ(ByteBuffer buffer, int index) {
        return buffer.getFloat(getBufferPosition(index) + 8);
    }

    protected byte getR(ByteBuffer buffer, int index) {
        return buffer.get(getBufferPosition(index) + 12);
    }

    protected byte getG(ByteBuffer buffer, int index) {
        return buffer.get(getBufferPosition(index) + 13);
    }

    protected byte getB(ByteBuffer buffer, int index) {
        return buffer.get(getBufferPosition(index) + 14);
    }

    protected byte getA(ByteBuffer buffer, int index) {
        return buffer.get(getBufferPosition(index) + 15);
    }

    protected float getU(ByteBuffer buffer, int index) {
        return buffer.getFloat(getBufferPosition(index) + 16);
    }

    protected float getV(ByteBuffer buffer, int index) {
        return buffer.getFloat(getBufferPosition(index) + 20);
    }

    protected int getLight(ByteBuffer buffer, int index) {
        return buffer.getInt(getBufferPosition(index) + 24);
    }

    protected byte getNX(ByteBuffer buffer, int index) {
        return buffer.get(getBufferPosition(index) + 28);
    }

    protected byte getNY(ByteBuffer buffer, int index) {
        return buffer.get(getBufferPosition(index) + 29);
    }

    protected byte getNZ(ByteBuffer buffer, int index) {
        return buffer.get(getBufferPosition(index) + 30);
    }
}
