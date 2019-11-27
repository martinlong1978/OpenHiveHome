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
package org.openhab.binding.hivehome.handler;

import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.thing.Bridge;
import org.openhab.binding.hivehome.internal.HiveHomeBindingConstants;
import org.openhab.binding.hivehome.internal.HiveHttpClient;

/**
 * The {@link HiveHomeBindingConstants} class defines common constants, which
 * are used across the whole binding.
 *
 * @author Martin Long - Initial contribution
 */
public class HiveBridgeHandler extends BaseBridgeHandler {

    private HiveHttpClient httpClient;

    public HiveBridgeHandler(Bridge thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {

    }

    public HiveHttpClient getSmartthingsHttpClient() {
        return httpClient;
    }

    public void initialize() {
        try {
            this.httpClient = new HiveHttpClient();
        } catch (Exception e) {
        }
    }

}
