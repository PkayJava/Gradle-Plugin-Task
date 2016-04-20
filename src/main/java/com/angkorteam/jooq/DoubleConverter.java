package com.angkorteam.jooq;

import org.jooq.impl.AbstractConverter;

import java.math.BigDecimal;

/**
 * Created by Khauv Socheat on 4/20/2016.
 */
public class DoubleConverter extends AbstractConverter<BigDecimal, Double> {

    public DoubleConverter() {
        super(BigDecimal.class, Double.class);
    }

    @Override
    public Double from(BigDecimal databaseObject) {
        if (databaseObject != null) {
            return databaseObject.doubleValue();
        }
        return null;
    }

    @Override
    public BigDecimal to(Double userObject) {
        if (userObject != null) {
            return new BigDecimal(userObject);
        }
        return null;
    }
}
