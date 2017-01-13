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

/**
 * A buffer, which can grow when needed.
 * 
 * <p>
 * This buffer {@link #canGrow()} returns <code>false</code>, if {@link #limit(int)} or
 * {@link #flip()} called. The buffer will be back to grow-able again after {@link #clear()} called.
 * </p>
 * 
 * <p>
 * Following methods will makes buffer grow:
 * <ul>
 * <li>{@link Buffer#putByte(byte)}</li>
 * <li>{@link Buffer#putChar(char)}</li>
 * <li>{@link Buffer#putShort(short)}</li>
 * <li>{@link Buffer#putInt(int)}</li>
 * <li>{@link Buffer#putLong(long)}</li>
 * <li>{@link Buffer#putFloat(float)}</li>
 * <li>{@link Buffer#putDouble(double)}</li>
 * </ul>
 * </p>
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
public interface AutoBuffer extends Buffer {
    /**
     * Returns <code>false</code> if {@link #flip()} or {@link #limit(int)} called. Call
     * {@link #clear()} to make the buffer grow-able again.
     * 
     * @return <code>true</code> if this buffer can grow.
     */
    boolean canGrow();
}
