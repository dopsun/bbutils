/*
 * Copyright (c) 2017 Dop Sun. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dopsun.bbutils;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author Dop Sun
 * @since 1.0.0
 */
final class ByteBufferFixedBuffer implements FixedBuffer, HasByteBuffer {
    private static final Field markField;

    static {
        try {
            Field field = Buffer.class.getDeclaredField("mark");
            field.setAccessible(true);

            markField = field;
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private final ByteBuffer byteBuffer;

    ByteBufferFixedBuffer(ByteBuffer byteBuffer) {
        Objects.requireNonNull(byteBuffer);

        this.byteBuffer = byteBuffer;
    }

    @Override
    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }

    @Override
    public int capacity() {
        return byteBuffer.capacity();
    }

    @Override
    public int position() {
        return byteBuffer.position();
    }

    @Override
    public void position(int newPosition) {
        byteBuffer.position(newPosition);
    }

    @Override
    public int limit() {
        return byteBuffer.limit();
    }

    @Override
    public void limit(int newLimit) {
        byteBuffer.limit(newLimit);
    }

    @Override
    public void mark() {
        byteBuffer.mark();
    }

    @Override
    public int markValue() {
        try {
            return markField.getInt(byteBuffer);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reset() {
        byteBuffer.reset();
    }

    @Override
    public void clear() {
        byteBuffer.clear();
    }

    @Override
    public void flip() {
        byteBuffer.flip();
    }

    @Override
    public void rewind() {
        byteBuffer.rewind();
    }

    @Override
    public int remaining() {
        return byteBuffer.remaining();
    }

    @Override
    public boolean hasRemaining() {
        return byteBuffer.hasRemaining();
    }

    @Override
    public void putBuffer(Buffer buffer) {
        Objects.requireNonNull(buffer);
        if (!(buffer instanceof HasByteBuffer)) {
            throw new IllegalArgumentException("Unsupported buffer: " + buffer);
        }

        HasByteBuffer hasByteBuffer = (HasByteBuffer) buffer;
        byteBuffer.put(hasByteBuffer.getByteBuffer());
    }

    @Override
    public byte getByte() {
        return byteBuffer.get();
    }

    @Override
    public byte getByte(int index) {
        return byteBuffer.get(index);
    }

    @Override
    public void putByte(byte value) {
        byteBuffer.put(value);
    }

    @Override
    public void putByte(int index, byte value) {
        byteBuffer.put(index, value);
    }

    @Override
    public char getChar() {
        return byteBuffer.getChar();
    }

    @Override
    public char getChar(int index) {
        return byteBuffer.getChar(index);
    }

    @Override
    public void putChar(char value) {
        byteBuffer.putChar(value);
    }

    @Override
    public void putChar(int index, char value) {
        byteBuffer.putChar(index, value);
    }

    @Override
    public short getShort() {
        return byteBuffer.getShort();
    }

    @Override
    public short getShort(int index) {
        return byteBuffer.getShort(index);
    }

    @Override
    public void putShort(short value) {
        byteBuffer.putShort(value);
    }

    @Override
    public void putShort(int index, short value) {
        byteBuffer.putShort(index, value);
    }

    @Override
    public int getInt() {
        return byteBuffer.getInt();
    }

    @Override
    public int getInt(int index) {
        return byteBuffer.getInt(index);
    }

    @Override
    public void putInt(int value) {
        byteBuffer.putInt(value);
    }

    @Override
    public void putInt(int index, int value) {
        byteBuffer.putInt(index, value);
    }

    @Override
    public long getLong() {
        return byteBuffer.getLong();
    }

    @Override
    public long getLong(int index) {
        return byteBuffer.getLong(index);
    }

    @Override
    public void putLong(long value) {
        byteBuffer.putLong(value);
    }

    @Override
    public void putLong(int index, long value) {
        byteBuffer.putLong(index, value);
    }

    @Override
    public float getFloat() {
        return byteBuffer.getFloat();
    }

    @Override
    public float getFloat(int index) {
        return byteBuffer.getFloat(index);
    }

    @Override
    public void putFloat(float value) {
        byteBuffer.putFloat(value);
    }

    @Override
    public void putFloat(int index, float value) {
        byteBuffer.putFloat(index, value);
    }

    @Override
    public double getDouble() {
        return byteBuffer.getDouble();
    }

    @Override
    public double getDouble(int index) {
        return byteBuffer.getDouble(index);
    }

    @Override
    public void putDouble(double value) {
        byteBuffer.putDouble(value);
    }

    @Override
    public void putDouble(int index, double value) {
        byteBuffer.putDouble(index, value);
    }
}
