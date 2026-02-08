package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DestroyDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("DestroyDate JSON Serialization Tests")
class DestroyDateJsonTest extends AbstractJsonSerializationTestSuite<DestroyDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<DestroyDate> type() {
        return DestroyDate.class;
    }

    @Override
    public DestroyDate createDefault() {

        return DestroyDate.builder().value(FIXED_TIME).build();
    }

    @Override
    public DestroyDate createVariant() {

        return DestroyDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
