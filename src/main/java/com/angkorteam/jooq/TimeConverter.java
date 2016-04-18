package com.angkorteam.jooq;

import org.jooq.impl.AbstractConverter;

import java.util.Date;

/**
 * Created by socheat on 2/20/16.
 */
public class TimeConverter extends AbstractConverter<java.sql.Time, Date> {

    public TimeConverter() {
        super(java.sql.Time.class, Date.class);
    }

    @Override
    public Date from(java.sql.Time databaseObject) {
        if (databaseObject != null) {
            return databaseObject;
        }
        return null;
    }

    @Override
    public java.sql.Time to(Date userObject) {
        if (userObject != null) {
            return new java.sql.Time(userObject.getTime());
        }
        return null;
    }

}
