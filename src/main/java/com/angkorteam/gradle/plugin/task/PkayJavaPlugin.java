package com.angkorteam.gradle.plugin.task;

import org.gradle.api.Project;

/**
 * Created by socheat on 4/12/16.
 */
public class PkayJavaPlugin implements org.gradle.api.Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions().create("pkayJava", PkayJavaExtension.class);
        project.getTasks().withType(JooqTask.class);
        project.getTasks().create("jooq", JooqTask.class);
    }

}
