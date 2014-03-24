/*
 * Copyright (c) 2014, 4axka (Pty) Ltd
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package tech.anaxka.common.utility.builder;


import java.util.Map;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;


/**
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 */
public class MapBuilderNGTest {

    public MapBuilderNGTest() {
    }

    /**
     * Test of append method, of class MapBuilder.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        Object key = null;
        Object value = null;
        MapBuilder instance = null;
        MapBuilder expResult = null;
        MapBuilder result = instance.append(key, value);
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    /**
     * Test of build method, of class MapBuilder.
     */
    @Test
    public void testBuild() {
        System.out.println("build");
        MapBuilder instance = null;
        Map expResult = null;
        Map result = instance.build();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }
}
