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
 * Allocates buffer and releases if required.
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
public interface Allocator {
    /**
     * Allocates a new buffer. The capacity of returned buffer, is same or greater than
     * <code>capacity</code> requested.
     * 
     * @param capacity
     *            The new buffer's capacity, in bytes
     * @return The new buffer
     * 
     * @throws IllegalArgumentException
     *             If the <code>capacity</code> is a negative integer.
     * @throws IllegalStateException
     *             if this allocator does not support specified <code>capacity</code>.
     */
    Buffer alloc(int capacity);

    /**
     * Releases buffer. After released, the <code>buffer</code> should not be used anymore.
     * 
     * To implementor: Buffer allocated via {@link #alloc(int)} should always be released by
     * {@link #release(Buffer)} via same allocator. Otherwise, it may have memory leak.
     * 
     * @param buffer
     *            buffer to release.
     * 
     * @throws IllegalArgumentException
     *             if <code>buffer</code> cannot be released to this allocator.
     */
    void release(Buffer buffer);
}
