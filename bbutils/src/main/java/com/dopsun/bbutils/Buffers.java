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
 * Factory methods for {@link Buffer}.
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
public final class Buffers {
    /**
     * Wraps a {@link java.nio.ByteBuffer} as a {@link FixedBuffer}.
     * 
     * @param byteBuffer
     * @return fixed buffer
     */
    public static FixedBuffer wrap(ByteBuffer byteBuffer) {
        Objects.requireNonNull(byteBuffer);

        return new ByteBufferFixedBuffer(byteBuffer);
    }

    /**
     * Makes an {@link AutoBuffer} with <code>allocator</code> and <code>initCapacity</code>. The
     * returned auto buffer will double existing capacity every time grows.
     * 
     * @param allocator
     *            allocator for initialization and growing
     * @param initCapacity
     *            initial capacity, should be greater than zero and is power of 2.
     * 
     * @return an {@link AutoBuffer}
     */
    public static AutoBuffer pow2AutoBuffer(FixedBufferAllocator allocator, int initCapacity) {
        Objects.requireNonNull(allocator);
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("initCapacity invalid: " + initCapacity);
        }
        if (!isPow2(initCapacity)) {
            throw new IllegalArgumentException("initCapacity is not power of 2: " + initCapacity);
        }

        return new AutoBufferImpl(allocator, initCapacity, old -> old * 2);
    }

    /**
     * Make a new {@link AutoBuffer}, growing with Arithmetic Progression (AP).
     * 
     * @param allocator
     *            allocator for initialization and growing
     * @param initCapacity
     *            initial capacity, should be greater than zero.
     * @param difference
     *            common difference to grow, and should be greater than zero.
     * 
     * @return an {@link AutoBuffer}
     */
    public static AutoBuffer apAutoBuffer(FixedBufferAllocator allocator, int initCapacity,
            int difference) {
        Objects.requireNonNull(allocator);
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("initCapacity invalid: " + initCapacity);
        }
        if (difference <= 0) {
            throw new IllegalArgumentException("difference invalid: " + difference);
        }

        return new AutoBufferImpl(allocator, initCapacity, old -> old + difference);
    }

    /**
     * Make a new {@link AutoBuffer}, growing with Geometric Progression (GP).
     * 
     * @param allocator
     *            allocator for initialization and growing
     * @param initCapacity
     *            initial capacity, should be greater than zero.
     * @param ratio
     *            ratio to grow, should greater than one and
     *            <code>initCapacity * (ratio - 1) >= 1</code>.
     * 
     * @return an {@link AutoBuffer}
     */
    public static AutoBuffer gpAutoBuffer(FixedBufferAllocator allocator, int initCapacity,
            double ratio) {
        Objects.requireNonNull(allocator);
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("initCapacity invalid: " + initCapacity);
        }
        if (ratio <= 1) {
            throw new IllegalArgumentException("ratio invalid: " + ratio);
        }
        if (initCapacity * (ratio - 1) < 1) {
            throw new IllegalArgumentException("ratio too small: " + ratio);
        }

        return new AutoBufferImpl(allocator, initCapacity, old -> (int) (old * ratio));
    }

    /**
     * From Guava IntMath.
     */
    private static boolean isPow2(int x) {
        return x > 0 & (x & (x - 1)) == 0;
    }

}
