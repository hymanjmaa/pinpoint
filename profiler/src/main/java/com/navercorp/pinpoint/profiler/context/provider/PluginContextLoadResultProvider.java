/*
 * Copyright 2017 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler.context.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.navercorp.pinpoint.bootstrap.config.ProfilerConfig;
import com.navercorp.pinpoint.bootstrap.instrument.DynamicTransformTrigger;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentEngine;
import com.navercorp.pinpoint.profiler.context.ApplicationContext;
import com.navercorp.pinpoint.profiler.context.module.BootstrapJarPaths;
import com.navercorp.pinpoint.profiler.context.module.PluginJars;
import com.navercorp.pinpoint.profiler.plugin.DefaultPluginContextLoadResult;
import com.navercorp.pinpoint.profiler.plugin.PluginContextLoadResult;

import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.util.List;

/**
 * @author Woonduk Kang(emeroad)
 */
public class PluginContextLoadResultProvider implements Provider<PluginContextLoadResult> {

    private final ProfilerConfig profilerConfig;
    private final Instrumentation instrumentation;
    private final InstrumentEngine instrumentEngine;
    private final List<String> bootstrapJarPaths;
    private final URL[] pluginJars;
    private final ApplicationContext applicationContext;
    private final DynamicTransformTrigger dynamicTransformTrigger;

    @Inject
    public PluginContextLoadResultProvider(ProfilerConfig profilerConfig, ApplicationContext applicationContext, DynamicTransformTrigger dynamicTransformTrigger, Instrumentation instrumentation, InstrumentEngine instrumentEngine,
                                           @BootstrapJarPaths List<String> bootstrapJarPaths, @PluginJars URL[] pluginJars) {
        if (profilerConfig == null) {
            throw new NullPointerException("profilerConfig must not be null");
        }
        if (applicationContext == null) {
            throw new NullPointerException("applicationContext must not be null");
        }
        if (dynamicTransformTrigger == null) {
            throw new NullPointerException("dynamicTransformTrigger must not be null");
        }
        if (instrumentation == null) {
            throw new NullPointerException("instrumentation must not be null");
        }
        if (instrumentEngine == null) {
            throw new NullPointerException("instrumentEngine must not be null");
        }
        if (bootstrapJarPaths == null) {
            throw new NullPointerException("bootstrapJarPaths must not be null");
        }
        if (pluginJars == null) {
            throw new NullPointerException("pluginJars must not be null");
        }

        this.profilerConfig = profilerConfig;
        this.applicationContext = applicationContext;
        this.dynamicTransformTrigger = dynamicTransformTrigger;

        this.instrumentation = instrumentation;
        this.instrumentEngine = instrumentEngine;
        this.bootstrapJarPaths = bootstrapJarPaths;
        this.pluginJars = pluginJars;
    }

    @Override
    public PluginContextLoadResult get() {

        return new DefaultPluginContextLoadResult(profilerConfig, applicationContext, dynamicTransformTrigger, instrumentation, instrumentEngine, bootstrapJarPaths, pluginJars);

    }
}