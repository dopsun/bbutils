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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Dop Sun
 * @since 1.0.0
 */
final class ListFixedBufferPool implements FixedBufferPool {
    private final int bufferCapacity;
    private final List<FixedBuffer> list;

    ListFixedBufferPool(List<FixedBuffer> list) {
        this(list, true);
    }

    ListFixedBufferPool(FixedBuffer[] array) {
        this(Arrays.asList(array), false);
    }

    ListFixedBufferPool(List<FixedBuffer> list, boolean needCopy) {
        Objects.requireNonNull(list);
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }

        int capacity = -1;
        for (FixedBuffer buffer : list) {
            Objects.requireNonNull(buffer);

            if (capacity < 0) {
                capacity = buffer.capacity();
            } else {
                if (capacity != buffer.capacity()) {
                    throw new IllegalArgumentException("buffers are not in same capacity.");
                }
            }
        }

        this.bufferCapacity = capacity;
        this.list = needCopy ? new ArrayList<>(list) : list;
    }

    @Override
    public int bufferCapacity() {
        return this.bufferCapacity;
    }

    @Override
    public FixedBuffer borrowBuffer() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Pool is empty.");
        }

        return list.remove(list.size() - 1);
    }

    @Override
    public void returnBuffer(FixedBuffer buffer) {
        Objects.requireNonNull(buffer);

        if (buffer.capacity() != this.bufferCapacity) {
            throw new IllegalArgumentException("buffer is not for this pool.");
        }

        buffer.clear();

        // TODO: to check whether list has been full, instead of auto-grow if over return.
        this.list.add(buffer);
    }
}
