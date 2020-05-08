/*
 * Copyright (c) 2020 Jon Chambers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.eatthepath.pushy.apns.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

/**
 * An APNs payload builder that serializes payloads with a {@link Gson} instance. Callers may provide their own
 * {@code Gson} instance to change how the payload builder serializes custom properties.
 *
 * @author <a href="https://github.com/jchambers">Jon Chambers</a>
 *
 * @since 0.14.0
 */
public class GsonApnsPayloadBuilder extends ApnsPayloadBuilder {

    private final Gson gson;

    private static final Gson DEFAULT_GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .serializeNulls()
            .create();

    /**
     * Constructs a new payload builder with a default {@link Gson} instance.
     */
    public GsonApnsPayloadBuilder() {
        this(DEFAULT_GSON);
    }

    /**
     * Constructs a new payload builder with the given {@code Gson} instance.
     *
     * @param gson the {@code Gson} instance with which to serialize APNs payloads
     */
    public GsonApnsPayloadBuilder(final Gson gson) {
        this.gson = Objects.requireNonNull(gson);
    }

    @Override
    public String build() {
        return this.gson.toJson(this.buildPayloadMap());
    }

    @Override
    public String buildMdmPayload(final String pushMagicValue) {
        return this.gson.toJson(this.buildMdmPayloadMap(pushMagicValue));
    }
}
