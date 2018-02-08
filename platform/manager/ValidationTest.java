package com.ronglian.fssc.webapp.platform.manager;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class ValidationTest {
    private Validator validator;

    @Before
    public void init() {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

    }

    @Test
    public void prereqsMet() {
        // Workshop validWorkshop = new Workshop(2, 2, true, 3);
        // Set<ConstraintViolation<?>> violations = this.validator.validate(validWorkshop);
        // assertTrue(violations.isEmpty());
    }
}
