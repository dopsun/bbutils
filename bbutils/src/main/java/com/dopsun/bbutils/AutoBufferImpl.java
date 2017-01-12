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
    public int position() {
        return fixedBuffer.position();
    }

    @Override
    public void position(int newPosition) {
        fixedBuffer.position(newPosition);
    }

    @Override
    public void flip() {
        fixedBuffer.flip();
    }

    @Override
    public void clear() {
        fixedBuffer.clear();
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

    private void ensureCapacity(int deltaCapacity) {
        int expectedCapacity = fixedBuffer.position() + deltaCapacity;
        if (fixedBuffer.capacity() >= expectedCapacity) {
            return;
        }

        int nextCapacity = nextCapacityProducer.applyAsInt(fixedBuffer.capacity());
        while (nextCapacity < expectedCapacity) {
            nextCapacity = nextCapacityProducer.applyAsInt(nextCapacity);
        }

        FixedBuffer newBuffer = allocator.alloc(nextCapacity);
        fixedBuffer.flip();
        newBuffer.putBuffer(fixedBuffer);

        allocator.release(fixedBuffer);
        fixedBuffer = newBuffer;
    }
}
