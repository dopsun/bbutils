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
 * @author Dop Sun
 * @since 1.0.0
 */
public interface FixedBufferAllocator extends Allocator {
    @Override
    FixedBuffer alloc(int capacity);

    /**
     * Releases a fixed buffer.
     * 
     * @param buffer
     *            buffer to release
     * 
     * @see Allocator#release(Buffer)
     */
    void release(FixedBuffer buffer);

    @Override
    default void release(Buffer buffer) {
        Objects.requireNonNull(buffer);

        if (!(buffer instanceof FixedBuffer)) {
            throw new IllegalArgumentException("buffer is not FixedBuffer.");
        }

        release((FixedBuffer) buffer);
    }
}