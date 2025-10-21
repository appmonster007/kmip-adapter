package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.LastChangeDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("LastChangeDate JSON Serialization Tests")
class LastChangeDateJsonTest extends AbstractJsonSerializationSuite<LastChangeDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<LastChangeDate> type() {
        return LastChangeDate.class;
    }

    @Override
    protected LastChangeDate createDefault() {
        // TODO: Update with actual default values for your dataType
        return LastChangeDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected LastChangeDate createVariant() {
        // TODO: Update with different values to test variations
        return LastChangeDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
