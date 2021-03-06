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

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author Dop Sun
 * @since 1.0.0
 */
@SuppressWarnings("javadoc")
public class AutoBufferTest extends AbstractBufferTest {
    private static FixedBufferAllocator allocator;

    @BeforeClass
    public static void beforeClass() {
        allocator = Allocators.heap();
    }

    @AfterClass
    public static void afterClass() {

    }

    @Override
    protected AutoBuffer allocBuffer(int capacity) {
        return Buffers.apAutoBuffer(allocator, capacity, capacity);
    }

    @Override
    protected void releaseBuffer(Buffer buffer) {

    }

}
