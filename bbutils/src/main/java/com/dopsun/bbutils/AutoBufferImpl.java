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

import java.nio.BufferOverflowException;
import java.util.Objects;
import java.util.function.IntUnaryOperator;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * An auto buffer, will double existing capacity once grown.
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
@NotThreadSafe
final class AutoBufferImpl implements AutoBuffer {
    private final FixedBufferAllocator allocator;

    private final IntUnaryOperator nextCapacityProducer;

    /** this is not null, but can be changed. */
    private FixedBuffer fixedBuffer;
    private boolean canGrow = true;

    /**
     * @param allocator
     *            allocator for initialization and growing
     * @param initCapacity
     *            initial capacity, should be greater than zero.
     * @param nextCapacityProducer
     *            a function to produce next capacity based on current capacity
     * 
     * @throws IllegalArgumentException
     *             if <code>initCapacity</code> is less than or equal to zero.
     */
    public AutoBufferImpl(FixedBufferAllocator allocator, int initCapacity,
            IntUnaryOperator nextCapacityProducer) {
        Objects.requireNonNull(allocator);
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("initCapacity invalid: " + initCapacity);
        }
        Objects.requireNonNull(nextCapacityProducer);

        this.allocator = allocator;
        this.nextCapacityProducer = nextCapacityProducer;

        this.fixedBuffer = allocator.alloc(initCapacity);
    }

    @Override
    public boolean canGrow() {
        return this.canGrow;
    }

    @Override
    public int position() {
        return fixedBuffer.position();
    }

    @Override
    public void position(int newPosition) {
        fixedBuffer.position(newPosition);
    }

    @Override
    public int limit() {
        return fixedBuffer.limit();
    }

    @Override
    public void limit(int newLimit) {
        fixedBuffer.limit(newLimit);
        canGrow = false;
    }

    @Override
    public void mark() {
        fixedBuffer.mark();
    }

    @Override
    public int markValue() {
        return fixedBuffer.markValue();
    }

    @Override
    public void reset() {
        fixedBuffer.reset();
    }

    @Override
    public void clear() {
        fixedBuffer.clear();
        canGrow = true;
    }

    @Override
    public void flip() {
        fixedBuffer.flip();
        canGrow = false;
    }

    @Override
    public void rewind() {
        fixedBuffer.rewind();
    }

    @Override
    public int remaining() {
        return fixedBuffer.remaining();
    }

    @Override
    public boolean hasRemaining() {
        return fixedBuffer.hasRemaining();
    }

    @Override
    public void putBuffer(Buffer buffer) {
        Objects.requireNonNull(buffer);

        fixedBuffer.putBuffer(buffer);
    }

    @Override
    public byte getByte() {
        return fixedBuffer.getByte();
    }

    @Override
    public byte getByte(int index) {
        return fixedBuffer.getByte(index);
    }

    @Override
    public void putByte(byte value) {
        ensureCapacity(1);

        fixedBuffer.putByte(value);
    }

    @Override
    public void putByte(int index, byte value) {
        fixedBuffer.putByte(index, value);
    }

    @Override
    public char getChar() {
        return fixedBuffer.getChar();
    }

    @Override
    public char getChar(int index) {
        return fixedBuffer.getChar(index);
    }

    @Override
    public void putChar(char value) {
        ensureCapacity(2);

        fixedBuffer.putChar(value);
    }

    @Override
    public void putChar(int index, char value) {
        fixedBuffer.putChar(index, value);
    }

    @Override
    public short getShort() {
        return fixedBuffer.getShort();
    }

    @Override
    public short getShort(int index) {
        return fixedBuffer.getShort(index);
    }

    @Override
    public void putShort(short value) {
        ensureCapacity(2);

        fixedBuffer.putShort(value);
    }

    @Override
    public void putShort(int index, short value) {
        fixedBuffer.putShort(index, value);
    }

    @Override
    public int getInt() {
        return fixedBuffer.getInt();
    }

    @Override
    public int getInt(int index) {
        return fixedBuffer.getInt(index);
    }

    @Override
    public void putInt(int value) {
        ensureCapacity(4);

        fixedBuffer.putInt(value);
    }

    @Override
    public void putInt(int index, int value) {
        fixedBuffer.putInt(index, value);
    }

    @Override
    public long getLong() {
        return fixedBuffer.getLong();
    }

    @Override
    public long getLong(int index) {
        return fixedBuffer.getLong(index);
    }

    @Override
    public void putLong(long value) {
        ensureCapacity(8);

        fixedBuffer.putLong(value);
    }

    @Override
    public void putLong(int index, long value) {
        fixedBuffer.putLong(index, value);
    }

    @Override
    public float getFloat() {
        return fixedBuffer.getFloat();
    }

    @Override
    public float getFloat(int index) {
        return fixedBuffer.getFloat(index);
    }

    @Override
    public void putFloat(float value) {
        ensureCapacity(4);

        fixedBuffer.putFloat(value);
    }

    @Override
    public void putFloat(int index, float value) {
        fixedBuffer.putFloat(index, value);
    }

    @Override
    public double getDouble() {
        return fixedBuffer.getDouble();
    }

    @Override
    public double getDouble(int index) {
        return fixedBuffer.getDouble(index);
    }

    @Override
    public void putDouble(double value) {
        ensureCapacity(8);

        fixedBuffer.putDouble(value);
    }

    @Override
    public void putDouble(int index, double value) {
        fixedBuffer.putDouble(index, value);
    }

    /**
     * @param deltaCapacity
     * 
     * @throws BufferOverflowException
     *             if buffer {@link #canGrow()} is <code>false</code> but not enough space for data.
     */
    private void ensureCapacity(int deltaCapacity) {
        int expectedCapacity = fixedBuffer.position() + deltaCapacity;
        if (fixedBuffer.capacity() >= expectedCapacity) {
            return;
        }

        if (!canGrow) {
            throw new BufferOverflowException();
        }

        int nextCapacity = nextCapacityProducer.applyAsInt(fixedBuffer.capacity());
        while (nextCapacity < expectedCapacity) {
            nextCapacity = nextCapacityProducer.applyAsInt(nextCapacity);
        }

        int oldPosition = fixedBuffer.position();
        int oldMarkValue = fixedBuffer.markValue();

        fixedBuffer.position(0);

        FixedBuffer newBuffer = allocator.alloc(nextCapacity);
        fixedBuffer.flip();
        newBuffer.putBuffer(fixedBuffer);

        if (oldMarkValue >= 0) {
            newBuffer.position(oldMarkValue);
            newBuffer.mark();
        }

        if (oldPosition != newBuffer.position()) {
            newBuffer.position(oldPosition);
        }

        allocator.release(fixedBuffer);
        fixedBuffer = newBuffer;
    }
}
