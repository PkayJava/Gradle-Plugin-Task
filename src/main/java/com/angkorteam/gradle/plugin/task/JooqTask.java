package com.angkorteam.gradle.plugin.task;

import com.angkorteam.jooq.DateConverter;
import com.angkorteam.jooq.DefaultGeneratorStrategy;
import com.angkorteam.jooq.JavaGenerator;
import org.gradle.api.tasks.TaskAction;
import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.*;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by socheat on 4/12/16.
 */
public class JooqTask extends Task {

    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcDriver;
    private String jdbcUrl;
    private String database;
    private String dialect;
    private String packageName;
    private String outputDirectory;

    @TaskAction
    public void jooq() {
        org.jooq.util.jaxb.Configuration configuration = new org.jooq.util.jaxb.Configuration();

        Jdbc jdbc = new Jdbc();
        jdbc.setUser(this.jdbcUser);
        jdbc.setUrl(this.jdbcUrl);
        jdbc.setDriver(this.jdbcDriver);
        jdbc.setPassword(this.jdbcPassword);
        configuration.setJdbc(jdbc);

        Generator generator = new Generator();
        generator.setName(JavaGenerator.class.getName());

        Database database = new Database();
        database.setName(this.dialect);
        database.setRecordVersionFields("optimistic");
        database.setIncludes(".*");
        database.setInputSchema(this.database);

        CustomType customType = new CustomType();
        customType.setName(DateConverter.class.getSimpleName());
        customType.setType(Date.class.getName());
        customType.setConverter(DateConverter.class.getName());
        database.setCustomTypes(Arrays.asList(customType));

        ForcedType forcedType = new ForcedType();
        forcedType.setName(DateConverter.class.getSimpleName());
        forcedType.setExpression(".*");
        forcedType.setTypes("^(datetime)|(timestamp)|(time)|(date)$");
        database.setForcedTypes(Arrays.asList(forcedType));

        generator.setDatabase(database);

        Strategy strategy = new Strategy();
        strategy.setName(DefaultGeneratorStrategy.class.getName());
        generator.setStrategy(strategy);

        Generate generate = new Generate();
        generate.setDeprecated(false);
        generate.setRelations(true);
        generate.setInstanceFields(true);
        generate.setGlobalObjectReferences(true);
        generate.setPojosToString(true);
        generate.setImmutablePojos(true);
        generate.setDaos(true);
        generate.setPojos(true);
        generate.setPojosEqualsAndHashCode(true);
        generate.setRecords(true);
        generate.setInterfaces(true);
        generator.setGenerate(generate);

        Target target = new Target();
        target.setPackageName(this.packageName);
        target.setDirectory(new File(getProject().getProjectDir(), this.outputDirectory).getAbsolutePath());
        generator.setTarget(target);

        configuration.setGenerator(generator);
        try {
            GenerationTool.generate(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }
}