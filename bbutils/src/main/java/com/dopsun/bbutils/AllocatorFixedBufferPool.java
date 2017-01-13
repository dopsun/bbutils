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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An {@link FixedBufferPool} grows automatically.
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
final class AllocatorFixedBufferPool implements FixedBufferPool {
    private final FixedBufferAllocator allocator;
    private final int bufferCapacity;
    private final List<FixedBuffer> list;

    /**
     * @param allocator
     *            allocator to allocate the buffer.
     * @param bufferCapacity
     *            capacity of buffer for this pool.
     * @param initSize
     *            allocates this number of buffer if greater than zero.
     */
    public AllocatorFixedBufferPool(FixedBufferAllocator allocator, int bufferCapacity,
            int initSize) {
        Objects.requireNonNull(allocator);
        if (bufferCapacity <= 0) {
            throw new IllegalArgumentException("bufferCapacity");
        }

        this.allocator = allocator;
        this.bufferCapacity = bufferCapacity;

        if (initSize > 0) {
            list = new ArrayList<>(initSize);

            for (int i = 0; i < initSize; i++) {
                FixedBuffer buffer = allocator.alloc(bufferCapacity);
                list.add(buffer);
            }
        } else {
            list = new ArrayList<>();
        }
    }

    @Override
    public void close() throws Exception {
        for (FixedBuffer buffer : list) {
            allocator.release(buffer);
        }

        list.clear();
    }

    @Override
    public int bufferCapacity() {
        return bufferCapacity;
    }

    @Override
    public FixedBuffer borrowBuffer() {
        if (list.size() > 0) {
            return list.remove(list.size() - 1);
        }

        return allocator.alloc(bufferCapacity);
    }

    @Override
    public void returnBuffer(FixedBuffer buffer) {
        Objects.requireNonNull(buffer);

        if (buffer.capacity() != bufferCapacity) {
            throw new IllegalArgumentException();
        }

        buffer.clear();

        list.add(buffer);
    }

}
