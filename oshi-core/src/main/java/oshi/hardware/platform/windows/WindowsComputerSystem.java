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
package oshi.hardware.platform.windows;

import java.util.List;
import java.util.Map;

import oshi.hardware.common.AbstractComputerSystem;
import oshi.util.platform.windows.WmiUtil;

/**
 * Hardware data obtained from WMI
 * 
 * @author SchiTho1 [at] Securiton AG
 * @author widdis [at] gmail [dot] com
 */
final class WindowsComputerSystem extends AbstractComputerSystem {

    private static final long serialVersionUID = 1L;

    WindowsComputerSystem() {
        init();
    }

    private void init() {

        final Map<String, List<String>> win32ComputerSystem = WmiUtil.selectStringsFrom(null, "Win32_ComputerSystem",
                "Manufacturer,Model", null);

        final List<String> manufacturers = win32ComputerSystem.get("Manufacturer");
        if (manufacturers != null && !manufacturers.isEmpty()) {
            setManufacturer(manufacturers.get(0));
        }

        final List<String> models = win32ComputerSystem.get("Model");
        if (models != null && !models.isEmpty()) {
            setModel(models.get(0));
        }

        final Map<String, List<String>> win32BIOS = WmiUtil.selectStringsFrom(null, "Win32_BIOS", "SerialNumber",
                "where PrimaryBIOS=true");

        final List<String> serialNumbers = win32BIOS.get("SerialNumber");
        if (serialNumbers != null && !serialNumbers.isEmpty()) {
            setSerialNumber(serialNumbers.get(0));
        }

        setFirmware(new WindowsFirmware());

        setBaseboard(new WindowsBaseboard());
    }
}
