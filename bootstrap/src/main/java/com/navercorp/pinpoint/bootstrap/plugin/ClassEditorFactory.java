package com.navercorp.pinpoint.bootstrap.plugin;

public interface ClassEditorFactory {
    public ClassEditor get(ProfilerPluginContext context);
}
