/**
 * Oshi (https://github.com/dblock/oshi)
 *
 * Copyright (c) 2010 - 2017 The Oshi Project Team
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Maintainers:
 * dblock[at]dblock[dot]org
 * widdis[at]gmail[dot]com
 * enrico.bianchi[at]gmail[dot]com
 *
 * Contributors:
 * https://github.com/dblock/oshi/graphs/contributors
 */
package oshi.json.hardware;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import oshi.json.SystemInfo;

/**
 * Test Power Source
 */
public class PowerSourceTest {
    /**
     * Test power source.
     */
    @Test
    public void testPowerSource() {
        SystemInfo si = new SystemInfo();
        PowerSource[] psArr = si.getHardware().getPowerSources();
        for (PowerSource ps : psArr) {
            assertTrue(ps.getRemainingCapacity() >= 0 && ps.getRemainingCapacity() <= 1);
            double epsilon = 1E-6;
            assertTrue(ps.getTimeRemaining() > 0 || Math.abs(ps.getTimeRemaining() - -1) < epsilon
                    || Math.abs(ps.getTimeRemaining() - -2) < epsilon);
        }
    }
}
