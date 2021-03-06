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

/**
 * Factory methods for {@link Pool}.
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
public final class Pools {
    /**
     * Wraps an array of {@link FixedBuffer} as a pool. The maximum available items for the returned
     * pool is the number of buffers input.
     * <p>
     * All input buffers should be in same size, and not <code>null</code>.
     * </p>
     * 
     * @param buffers
     * @return a new FixedBufferPool.
     */
    public static FixedBufferPool wrap(FixedBuffer[] buffers) {
        Objects.requireNonNull(buffers);

        return new ListFixedBufferPool(buffers);
    }

    /**
     * Makes a pool from <code>allocator</code> with <code>bufferCapacity</code>.
     * 
     * @param allocator
     *            allocator to allocate the buffer.
     * @param bufferCapacity
     *            capacity of buffer for this pool.
     * 
     * @return a {@link FixedBuffer} pool.
     */
    public static FixedBufferPool fromAllocator(FixedBufferAllocator allocator,
            int bufferCapacity) {
        Objects.requireNonNull(allocator);
        if (bufferCapacity <= 0) {
            throw new IllegalArgumentException("bufferCapacity should be greater than zero.");
        }

        return new AllocatorFixedBufferPool(allocator, bufferCapacity, -1);
    }

    /**
     * Makes a pool from <code>allocator</code> with <code>bufferCapacity</code>. A number of buffer
     * is created initially.
     * 
     * @param allocator
     *            allocator to allocate the buffer.
     * @param bufferCapacity
     *            capacity of buffer for this pool.
     * @param initSize
     *            should be greater than zero.
     * 
     * @return a {@link FixedBuffer} pool.
     */
    public static FixedBufferPool fromAllocator(FixedBufferAllocator allocator, int bufferCapacity,
            int initSize) {
        Objects.requireNonNull(allocator);
        if (bufferCapacity <= 0) {
            throw new IllegalArgumentException("bufferCapacity should be greater than zero.");
        }
        if (initSize <= 0) {
            throw new IllegalArgumentException("initSize should be greater than zero.");
        }

        return new AllocatorFixedBufferPool(allocator, bufferCapacity, initSize);
    }
}
