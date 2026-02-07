package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DestroyDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("DestroyDate TTLV Serialization Tests")
class DestroyDateTtlvTest extends AbstractTtlvSerializationTestSuite<DestroyDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<DestroyDate> type() {
        return DestroyDate.class;
    }

    @Override
    protected DestroyDate createDefault() {

        return DestroyDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected DestroyDate createVariant() {

        return DestroyDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
