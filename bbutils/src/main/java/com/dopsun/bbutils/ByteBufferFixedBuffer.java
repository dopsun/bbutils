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

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author Dop Sun
 * @since 1.0.0
 */
final class ByteBufferFixedBuffer implements FixedBuffer, HasByteBuffer {
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
    public int position() {
        return byteBuffer.position();
    }

    @Override
    public void position(int newPosition) {
        byteBuffer.position(newPosition);
    }

    @Override
    public void flip() {
        byteBuffer.flip();
    }

    @Override
    public int capacity() {
        return byteBuffer.capacity();
    }

    @Override
    public void clear() {
        byteBuffer.clear();
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
}
