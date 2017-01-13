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
     * @return
     * 
     * @see ByteBuffer#limit()
     */
    int limit();

    /**
     * @param newLimit
     * 
     * @see ByteBuffer#limit(int)
     */
    void limit(int newLimit);

    /**
     * @see ByteBuffer#mark()
     */
    void mark();

    /**
     * This is <code>java.nio.Buffer#markValue()</code>.
     * 
     * @return mark position, -1 if mark is not set.
     */
    int markValue();

    /**
     * @see ByteBuffer#reset()
     */
    void reset();

    /**
     * @see ByteBuffer#clear()
     */
    void clear();

    /**
     * @see ByteBuffer#flip()
     */
    void flip();

    /**
     * @see ByteBuffer#rewind()
     */
    void rewind();

    /**
     * @return
     * 
     * @see ByteBuffer#remaining()
     */
    int remaining();

    /**
     * @return
     * 
     * @see ByteBuffer#hasRemaining()
     */
    boolean hasRemaining();

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

    /**
     * @return
     * 
     * @see ByteBuffer#getChar()
     */
    char getChar();

    /**
     * @param index
     * @return
     * 
     * @see ByteBuffer#getChar(int)
     */
    char getChar(int index);

    /**
     * @param value
     * 
     * @see ByteBuffer#putChar(char)
     */
    void putChar(char value);

    /**
     * @param index
     * @param value
     * 
     * @see ByteBuffer#putChar(int, char)
     */
    void putChar(int index, char value);

    /**
     * @return
     * 
     * @see ByteBuffer#getShort()
     */
    short getShort();

    /**
     * @param index
     * @return
     * 
     * @see ByteBuffer#getShort(int)
     */
    short getShort(int index);

    /**
     * @param value
     * 
     * @see ByteBuffer#putShort(short)
     */
    void putShort(short value);

    /**
     * @param index
     * @param value
     * 
     * @see ByteBuffer#putShort(int, short)
     */
    void putShort(int index, short value);

    /**
     * @return
     * 
     * @see ByteBuffer#getInt()
     */
    int getInt();

    /**
     * @param index
     * @return
     * 
     * @see ByteBuffer#getInt(int)
     */
    int getInt(int index);

    /**
     * @param value
     * 
     * @see ByteBuffer#putInt(int)
     */
    void putInt(int value);

    /**
     * @param index
     * @param value
     * 
     * @see ByteBuffer#putInt(int, int)
     */
    void putInt(int index, int value);

    /**
     * @return
     * 
     * @see ByteBuffer#getLong()
     */
    long getLong();

    /**
     * @param index
     * @return
     * 
     * @see ByteBuffer#getLong(int)
     */
    long getLong(int index);

    /**
     * @param value
     * 
     * @see ByteBuffer#putLong(long)
     */
    void putLong(long value);

    /**
     * @param index
     * @param value
     * 
     * @see ByteBuffer#putLong(int, long)
     */
    void putLong(int index, long value);

    /**
     * @return
     * 
     * @see ByteBuffer#getFloat()
     */
    float getFloat();

    /**
     * @param index
     * @return
     * 
     * @see ByteBuffer#getFloat(int)
     */
    float getFloat(int index);

    /**
     * @param value
     * 
     * @see ByteBuffer#putFloat(float)
     */
    void putFloat(float value);

    /**
     * @param index
     * @param value
     * 
     * @see ByteBuffer#putFloat(int, float)
     */
    void putFloat(int index, float value);

    /**
     * @return
     * 
     * @see ByteBuffer#getDouble()
     */
    double getDouble();

    /**
     * @param index
     * @return
     * 
     * @see ByteBuffer#getDouble(int)
     */
    double getDouble(int index);

    /**
     * @param value
     * 
     * @see ByteBuffer#putDouble(double)
     */
    void putDouble(double value);

    /**
     * @param index
     * @param value
     * 
     * @see ByteBuffer#putDouble(int, double)
     */
    void putDouble(int index, double value);
}
