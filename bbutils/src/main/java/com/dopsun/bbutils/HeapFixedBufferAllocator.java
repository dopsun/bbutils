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
final class HeapFixedBufferAllocator implements FixedBufferAllocator {

    @Override
    public FixedBuffer alloc(int capacity) {
        return Buffers.wrap(ByteBuffer.allocate(capacity));
    }

    @Override
    public void release(FixedBuffer buffer) {
        Objects.requireNonNull(buffer);

        // Nothing to be done for heap buffer.
    }

}
