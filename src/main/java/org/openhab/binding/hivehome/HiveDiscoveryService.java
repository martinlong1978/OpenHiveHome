/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.hivehome;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.openhab.binding.hivehome.handler.HiveBridgeHandler;
import org.openhab.binding.hivehome.internal.HiveHomeBindingConstants;
import org.openhab.binding.hivehome.internal.HiveHttpClient;

/**
 * The {@link HiveHomeBindingConstants} class defines common constants, which
 * are used across the whole binding.
 *
 * @author Martin Long - Initial contribution
 */
@NonNullByDefault
public class HiveDiscoveryService extends AbstractDiscoveryService {

    public HiveDiscoveryService(int timeout) throws IllegalArgumentException {
        super(timeout);
    }

    @Nullable
    private HiveHttpClient httpClient;
    @Nullable
    private HiveBridgeHandler bridgeHandler;

    public HiveDiscoveryService() throws IllegalArgumentException {
        super(HiveHomeBindingConstants.SUPPORTED_THING_TYPES_UIDS, 10000);
    }

    public HiveDiscoveryService(HiveBridgeHandler bridgeHandler) throws IllegalArgumentException {
        super(HiveHomeBindingConstants.SUPPORTED_THING_TYPES_UIDS, 10000);
        this.bridgeHandler = bridgeHandler;
    }

    @Override
    protected void startScan() {
        httpClient = bridgeHandler.getSmartthingsHttpClient();

    }

}
