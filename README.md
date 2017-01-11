# bbutils
java.nio.ByteBuffer related utilities.

## Features

* [Buffer](bbutils/src/main/java/com/dopsun.bbutils/Buffer.java) a buffer inspired from ``java.nio.ByteBuffer``.
  * [FixedBuffer](bbutils/src/main/java/com/dopsun.bbutils/FixedBuffer.java): a buffer with fixed size.
  * [AutoBuffer](bbutils/src/main/java/com/dopsun.bbutils/AutoBuffer.java): a buffer grows when required.
  * [Buffers](bbutils/src/main/java/com/dopsun.bbutils/Buffers.java): factory methods for ``Buffer``.
* [Allocator](bbutils/src/main/java/com/dopsun.bbutils/Allocator.java) allocator for ``Buffer``.
  * [FixedBufferAllocator](bbutils/src/main/java/com/dopsun.bbutils/FixedBufferAllocator.java): allocator for ``FixedBufferAllocator``.
  * [Allocators](bbutils/src/main/java/com/dopsun.bbutils/Allocators.java): factory methods for ``Allocator``.
* [Pool](bbutils/src/main/java/com/dopsun.bbutils/Pool.java) pool for ``Buffer``.
  * [FixedBufferPool](bbutils/src/main/java/com/dopsun.bbutils/FixedBufferPool.java) pool for ``FixedBufferAllocator``.
  * [Pools](bbutils/src/main/java/com/dopsun.bbutils/Pools.java) factory methods for ``Pool``.
  