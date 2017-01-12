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
    @SuppressWarnings("unused")
    private final Allocator allocator;

    @SuppressWarnings("unused")
    private final IntUnaryOperator nextCapacityProducer;

    /** this is not null, but can be changed. */
    private Buffer buffer;

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
    public AutoBufferImpl(Allocator allocator, int initCapacity,
            IntUnaryOperator nextCapacityProducer) {
        Objects.requireNonNull(allocator);
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("initCapacity invalid: " + initCapacity);
        }
        Objects.requireNonNull(nextCapacityProducer);

        this.allocator = allocator;
        this.nextCapacityProducer = nextCapacityProducer;

        this.buffer = allocator.alloc(initCapacity);
    }

    @Override
    public void clear() {
        buffer.clear();
    }
}
