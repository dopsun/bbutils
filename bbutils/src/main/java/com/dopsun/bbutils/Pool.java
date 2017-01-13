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
 * A pool for {@link Buffer}.
 * 
 * @author Dop Sun
 * @since 1.0.0
 */
public interface Pool extends AutoCloseable {
    /**
     * Borrows a buffer from pool.
     * 
     * @return byte buffer borrowed from pool.
     * 
     * @throws IllegalStateException
     *             if pool is not available.
     */
    Buffer borrowBuffer();

    /**
     * Returns buffer to the pool.{@link Buffer#clear()} is called if returned successfully.
     * 
     * @param buffer
     *            byte buffer to return.
     * 
     * @throws IllegalArgumentException
     *             If the <code>buffer</code> does not belong to this pool.
     */
    void returnBuffer(Buffer buffer);
}
