package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ValidityDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ValidityDate JSON Serialization Tests")
class ValidityDateJsonTest extends AbstractJsonSerializationTestSuite<ValidityDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<ValidityDate> type() {
        return ValidityDate.class;
    }

    @Override
    protected ValidityDate createDefault() {

        return ValidityDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected ValidityDate createVariant() {

        return ValidityDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}