package com.angkorteam.gradle.plugin.task;

import org.gradle.api.DefaultTask;

/**
 * Created by socheat on 4/12/16.
 */
public abstract class Task extends DefaultTask {

    @Override
    public final String getGroup() {
        return "PkayJava";
    }

    @Override
    public final String getDescription() {
        return "AngkorTeam";
    }

    protected PkayJavaExtension getExtension() {
        return getProject().getExtensions().findByType(PkayJavaExtension.class);
    }
}
