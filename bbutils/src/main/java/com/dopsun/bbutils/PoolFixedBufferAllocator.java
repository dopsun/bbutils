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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.IntFunction;

/**
 * @author Dop Sun
 * @since 1.0.0
 */
final class PoolFixedBufferAllocator implements FixedBufferAllocator {
    private final IntFunction<FixedBufferPool> poolFactory;

    private final Map<Integer, FixedBufferPool> poolByCapacity = new HashMap<>();

    /**
     * @param poolFactory
     */
    public PoolFixedBufferAllocator(IntFunction<FixedBufferPool> poolFactory) {
        Objects.requireNonNull(poolFactory);

        this.poolFactory = poolFactory;
    }

    @Override
    public FixedBuffer alloc(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }

        FixedBufferPool pool = poolByCapacity.get(capacity);
        if (pool == null) {
            pool = poolFactory.apply(capacity);
            poolByCapacity.put(capacity, pool);
        }

        return pool.borrowBuffer();
    }

    @Override
    public void release(FixedBuffer buffer) {
        Objects.requireNonNull(buffer);

        FixedBufferPool pool = poolByCapacity.get(buffer.capacity());
        if (pool == null) {
            throw new IllegalArgumentException("buffer is not for this allocator.");
        }

        pool.returnBuffer(buffer);
    }

}
