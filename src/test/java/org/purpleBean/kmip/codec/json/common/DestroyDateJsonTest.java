package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DestroyDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("DestroyDate JSON Serialization Tests")
class DestroyDateJsonTest extends AbstractJsonSerializationSuite<DestroyDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<DestroyDate> type() {
        return DestroyDate.class;
    }

    @Override
    protected DestroyDate createDefault() {
        // TODO: Update with actual default values for your dataType
        return DestroyDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected DestroyDate createVariant() {
        // TODO: Update with different values to test variations
        return DestroyDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
