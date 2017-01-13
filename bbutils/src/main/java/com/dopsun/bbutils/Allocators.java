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
import java.util.function.IntFunction;

/**
 * Factory methods for {@link Allocator}.
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
public final class Allocators {

    /** Sync for lazy initialized fields. */
    private static final Object lazy_sync = new Object();

    private static volatile FixedBufferAllocator directAllocator;
    private static volatile FixedBufferAllocator heapAllocator;

    /**
     * @return allocator with direct memory.
     */
    public static FixedBufferAllocator direct() {
        FixedBufferAllocator alloc = directAllocator;
        if (alloc != null) {
            return alloc;
        }

        synchronized (lazy_sync) {
            alloc = directAllocator;
            if (alloc != null) {
                return alloc;
            }

            alloc = new DirectFixedBufferAllocator();
            directAllocator = alloc;
            return alloc;
        }
    }

    /**
     * @return allocator with heap memory.
     */
    public static FixedBufferAllocator heap() {
        FixedBufferAllocator alloc = heapAllocator;
        if (alloc != null) {
            return alloc;
        }

        synchronized (lazy_sync) {
            alloc = heapAllocator;
            if (alloc != null) {
                return alloc;
            }

            alloc = new HeapFixedBufferAllocator();
            heapAllocator = alloc;
            return alloc;
        }
    }

    /**
     * Creates an allocator based on pool.
     * 
     * @param poolFactory
     *            factory to create pool for specified capacity.
     * @return an allocator which allocates from pool
     */
    public static FixedBufferAllocator fromPool(IntFunction<FixedBufferPool> poolFactory) {
        Objects.requireNonNull(poolFactory);

        return new PoolFixedBufferAllocator(poolFactory);
    }
}
