/*
 * Copyright (c) 2014, 4axka (Pty) Ltd
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   1. Redistributions of source code must retain the above copyright notice, this
 *      list of conditions and the following disclaimer.
 * 
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 * 
 *   3. Neither the name of the copyright holder nor the names of its contributors
 *      may be used to endorse or promote products derived from this software
 *      without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tech.anaxka.util.random;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 */
public final class FastQualityRandom
        extends Random {

    private final Lock __lock;
    private long __u;
    private long __v = 4101842887655102017L;
    private long __w = 1;

    public FastQualityRandom() {
        this(System.nanoTime());
        //__lock = new ReentrantLock();
    }

    public FastQualityRandom(long seed) {
        __lock = new ReentrantLock();
        try {
            __lock.lock();
            __u = seed ^ __v;
            nextLong();
            __v = __u;
            nextLong();
            __w = __v;
            nextLong();
        } finally {
            __lock.unlock();
        }
    }

    @Override
    public long nextLong() {
        try {
            __lock.lock();
            __u = __u * 2862933555777941757L + 7046029254386353087L;
            __v ^= __v >>> 17;
            __v ^= __v << 31;
            __v ^= __v >>> 8;
            __w = 4294957665L * (__w & 0xffffffff) + (__w >>> 32);
            long x = __u ^ (__u << 21);
            x ^= x >>> 35;
            x ^= x << 4;
            long ret = (x + __v) ^ __w;
            return ret;
        } finally {
            __lock.unlock();
        }
    }

    @Override
    protected int next(int bits) {
        return (int) (nextLong() >>> (64 - bits));
    }
}
