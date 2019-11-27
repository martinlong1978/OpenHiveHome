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
package org.openhab.binding.hivehome.internal;

import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

import com.google.common.collect.ImmutableSet;

/**
 * The {@link HiveHomeBindingConstants} class defines common constants, which
 * are used across the whole binding.
 *
 * @author Martin Long - Initial contribution
 */
@NonNullByDefault
public class HiveHomeBindingConstants {

    private static final String BINDING_ID = "hivehome";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_SAMPLE = new ThingTypeUID(BINDING_ID, "sample");

    // List of all Channel ids
    public static final String CHANNEL_1 = "channel1";

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = ImmutableSet.of(
            new ThingTypeUID(BINDING_ID, "temperatureMeasurement"), new ThingTypeUID(BINDING_ID, "thermostat"),
            new ThingTypeUID(BINDING_ID, "thermostatCoolingSetpoint"),
            new ThingTypeUID(BINDING_ID, "thermostatFanMode"),
            new ThingTypeUID(BINDING_ID, "thermostatHeatingSetpoint"), new ThingTypeUID(BINDING_ID, "thermostatMode"),
            new ThingTypeUID(BINDING_ID, "thermostatOperatingState"),
            new ThingTypeUID(BINDING_ID, "thermostatSetpoint"));

}
