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

/**
 * This is an abstraction of {@link ByteBuffer java.nio.ByteBuffer}. Unless otherwise documented,
 * all methods defined in this interface should be same as specified in {@link ByteBuffer
 * java.nio.ByteBuffer}.
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
public interface Buffer {
    /**
     * @return
     * 
     * @see ByteBuffer#position()
     */
    int position();

    /**
     * @param newPosition
     * 
     * @see ByteBuffer#position(int)
     */
    void position(int newPosition);

    /**
     * @see ByteBuffer#flip()
     */
    void flip();

    /**
     * @see ByteBuffer#clear()
     */
    void clear();

    /**
     * @param buffer
     * 
     * @see ByteBuffer#put(ByteBuffer)
     */
    void putBuffer(Buffer buffer);

    /**
     * @return
     * 
     * @see ByteBuffer#get()
     */
    byte getByte();

    /**
     * @param index
     * @return
     * 
     * @see ByteBuffer#get(int)
     */
    byte getByte(int index);

    /**
     * @param value
     * 
     * @see ByteBuffer#put(byte)
     */
    void putByte(byte value);

    /**
     * @param index
     * @param value
     * 
     * @see ByteBuffer#put(int, byte)
     */
    void putByte(int index, byte value);
}
