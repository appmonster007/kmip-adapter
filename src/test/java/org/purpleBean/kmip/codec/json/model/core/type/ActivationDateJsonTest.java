package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ActivationDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ActivationDate JSON Serialization Tests")
class ActivationDateJsonTest extends AbstractJsonSerializationTestSuite<ActivationDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<ActivationDate> type() {
        return ActivationDate.class;
    }

    @Override
    protected ActivationDate createDefault() {
        return ActivationDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected ActivationDate createVariant() {
        return ActivationDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}
