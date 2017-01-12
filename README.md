# bbutils
java.nio.ByteBuffer related utilities.

## Buffer, FixedBuffer and AutoBuffer
* [Buffer](bbutils/src/main/java/com/dopsun/bbutils/Buffer.java) a buffer inspired from ``java.nio.ByteBuffer``.
  * [FixedBuffer](bbutils/src/main/java/com/dopsun/bbutils/FixedBuffer.java): a buffer with fixed size.
  * [AutoBuffer](bbutils/src/main/java/com/dopsun/bbutils/AutoBuffer.java): a buffer grows when required.
  * [Buffers](bbutils/src/main/java/com/dopsun/bbutils/Buffers.java): factory methods for ``Buffer``.

### Buffer
To be added

### FixedBuffer
To be added

### AutoBuffer
There are 3 types of auto buffer:

* [Arithmetic Progression (AP)](https://en.wikipedia.org/wiki/Arithmetic_progression): grows following AP sequences, with initial capacity and difference.
  * Factory method: ``Buffers.apAutoBuffer(Allocator allocator, int initCapacity, int difference)``
* [Geometric Progression (GP)](https://en.wikipedia.org/wiki/Geometric_progression): grows following GP sequences, with initial capacity and ratio.
  * Factory method: ``Buffers.gpAutoBuffer(Allocator allocator, int initCapacity, double ratio)``
* [Power of 2 (POW2)](https://en.wikipedia.org/wiki/Power_of_two): grows with double capacity.
  * Factory method: ``Buffers.pow2AutoBuffer(Allocator allocator, int initCapacity)``

## Allocator
* [Allocator](bbutils/src/main/java/com/dopsun/bbutils/Allocator.java) allocator for ``Buffer``.
  * [FixedBufferAllocator](bbutils/src/main/java/com/dopsun/bbutils/FixedBufferAllocator.java): allocator for ``FixedBufferAllocator``.
  * [Allocators](bbutils/src/main/java/com/dopsun/bbutils/Allocators.java): factory methods for ``Allocator``.


## Pool
* [Pool](bbutils/src/main/java/com/dopsun/bbutils/Pool.java) pool for ``Buffer``.
  * [FixedBufferPool](bbutils/src/main/java/com/dopsun/bbutils/FixedBufferPool.java) pool for ``FixedBufferAllocator``.
  * [Pools](bbutils/src/main/java/com/dopsun/bbutils/Pools.java) factory methods for ``Pool``.
