package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ValidityDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ValidityDate TTLV Serialization Tests")
class ValidityDateTtlvTest extends AbstractTtlvSerializationSuite<ValidityDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<ValidityDate> type() {
        return ValidityDate.class;
    }

    @Override
    protected ValidityDate createDefault() {
        // TODO: Update with actual default values for your dataType
        return ValidityDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected ValidityDate createVariant() {
        // TODO: Update with different values to test variations
        return ValidityDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}