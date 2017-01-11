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

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Fixed buffer allocator from direct memory
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
final class DirectFixedBufferAllocator implements FixedBufferAllocator {

    private static final Object sync = new Object();
    private static volatile Field cleanerField;

    private static Field getClearnerField(ByteBuffer buffer) {
        if (cleanerField != null) {
            return cleanerField;
        }

        synchronized (sync) {
            if (cleanerField != null) {
                return cleanerField;
            }

            try {
                Field field = buffer.getClass().getDeclaredField("cleaner");
                field.setAccessible(true);

                cleanerField = field;
                return field;
            } catch (NoSuchFieldException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("restriction")
    private static void cleanDirectBuffer(ByteBuffer buffer) {
        Field field = getClearnerField(buffer);

        try {
            sun.misc.Cleaner cleaner = (sun.misc.Cleaner) field.get(buffer);
            cleaner.clean();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FixedBuffer alloc(int capacity) {
        return Buffers.wrap(ByteBuffer.allocateDirect(capacity));
    }

    @Override
    public void release(FixedBuffer buffer) {
        Objects.requireNonNull(buffer);

        if (!(buffer instanceof HasByteBuffer)) {
            throw new IllegalArgumentException("Not supported.");
        }

        ByteBuffer srcBuffer = ((HasByteBuffer) buffer).getByteBuffer();

        cleanDirectBuffer(srcBuffer);
    }
}
