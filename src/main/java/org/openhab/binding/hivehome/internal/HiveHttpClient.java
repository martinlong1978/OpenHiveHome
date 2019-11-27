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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * The {@link HiveHomeBindingConstants} class defines common constants, which
 * are used across the whole binding.
 *
 * @author Martin Long - Initial contribution
 */
public class HiveHttpClient {
    private final Logger logger = LoggerFactory.getLogger(HiveHttpClient.class);

    private HttpClient httpClient;
    private Gson gson;

    public HiveHttpClient() throws Exception {
        // Setup the jetty client for use in sending data to the Smartthings hub
        httpClient = new HttpClient();
        httpClient.start();

        // Get a Gson instance
        gson = new Gson();
    }

    public Map<String, Object> sendDeviceCommand(String path, String data)
            throws InterruptedException, TimeoutException, ExecutionException {
        ContentResponse response = httpClient.newRequest("https://beekeeper-uk.hivehome.com/1.0/")
                .timeout(3, TimeUnit.SECONDS).path(path).method(HttpMethod.POST)
                .content(new StringContentProvider(data), "application/json").send();

        Map<String, Object> result = null;

        int status = response.getStatus();
        if (status == 200) {
            String responseStr = response.getContentAsString();
            if (response != null && responseStr.length() > 0) {
                result = new HashMap<String, Object>();
                result = gson.fromJson(responseStr, result.getClass());
            }
        } else {
            logger.info("Sent message \"{}\" with path \"{}\" to the Hive hub, recieved HTTP status {}", data, path,
                    status);
        }

        return result;
    }

    public Map<String, Object> fetchDeviceInfo(String path)
            throws InterruptedException, TimeoutException, ExecutionException {
        ContentResponse response = httpClient.newRequest("https://beekeeper-uk.hivehome.com/1.0/")
                .timeout(3, TimeUnit.SECONDS).path(path).method(HttpMethod.GET).send();

        Map<String, Object> result = null;

        int status = response.getStatus();
        if (status == 200) {
            String responseStr = response.getContentAsString();
            if (response != null && responseStr.length() > 0) {
                result = new HashMap<String, Object>();
                result = gson.fromJson(responseStr, result.getClass());
            }
        } else {
            logger.info("Fetch with path \"{}\" to the Hive hub, recieved HTTP status {}", path, status);
        }

        return result;
    }

    public void stopHttpClient() {
        try {
            httpClient.stop();
            logger.info("HTTP Client stopped");
        } catch (Exception e) {
            logger.warn("HTTP client failed to stop because: {}", e.getMessage());
        }
    }
}
