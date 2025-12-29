package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@DisplayName("ValidityDate Domain Tests")
class ValidityDateTest extends AbstractKmipDataTypeSuite<ValidityDate> {

    @Override
    protected Class<ValidityDate> type() {
        return ValidityDate.class;
    }

    @Override
    protected ValidityDate createDefault() {
        // TODO: Update with actual default values for your dataType
        OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        return ValidityDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        // TODO: Update with actual encoding type for your dataType
        return EncodingType.DATE_TIME;
    }
}