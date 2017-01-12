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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Dop Sun
 * @since 1.0.0
 */
@SuppressWarnings("javadoc")
public abstract class AbstractBufferTest {
    /**
     * @param capacity
     * @return makes a new buffer for testing.
     */
    protected abstract Buffer allocBuffer(int capacity);

    /**
     * Releases the buffer
     * 
     * @param buffer
     */
    protected abstract void releaseBuffer(Buffer buffer);

    @Test
    public void bufferGivenEnoughCapacityWhenPutByteThenPositionAdvanceOne() {
        Buffer buffer = allocBuffer(4);
        try {
            int oldPosition = buffer.position();

            buffer.putByte(Byte.MIN_VALUE);

            Assert.assertEquals(oldPosition + 1, buffer.position());
        } finally {
            releaseBuffer(buffer);
        }
    }

    @Test
    public void bufferGivenEnoughCapacityWhenPutByteWithIndexThenPositionNotChange() {
        Buffer buffer = allocBuffer(4);
        try {
            int oldPosition = buffer.position();

            buffer.putByte(1, Byte.MIN_VALUE);

            Assert.assertEquals(oldPosition, buffer.position());
        } finally {
            releaseBuffer(buffer);
        }
    }

    @Test
    public void bufferGivenEnoughCapacityWhenGetByteThenPositionAdvanceOne() {
        Buffer buffer = allocBuffer(4);
        try {
            int oldPosition = buffer.position();

            buffer.getByte();

            Assert.assertEquals(oldPosition + 1, buffer.position());
        } finally {
            releaseBuffer(buffer);
        }
    }

    @Test
    public void bufferGivenEnoughCapacityWhenGetByteWithIndexThenPositionNotChange() {
        Buffer buffer = allocBuffer(4);
        try {
            int oldPosition = buffer.position();

            buffer.getByte(2);

            Assert.assertEquals(oldPosition, buffer.position());
        } finally {
            releaseBuffer(buffer);
        }
    }

    @Test
    public void bufferGivenEnoughCapacityWhenPutByteThenGetByteReturnSameValue() {
        Buffer buffer = allocBuffer(4);
        try {
            int oldPosition = buffer.position();

            buffer.putByte(Byte.MIN_VALUE);

            Assert.assertEquals(Byte.MIN_VALUE, buffer.getByte(oldPosition));
        } finally {
            releaseBuffer(buffer);
        }
    }

}
