package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CustomAttribute TTLV Serialization Tests")
class CustomAttributeTtlvTest extends AbstractTtlvSerializationTestSuite<CustomAttribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<CustomAttribute> type() {
        return CustomAttribute.class;
    }

    @Override
    public CustomAttribute createDefault() {
        return CustomAttribute.of("x-custom-state", AttributeValue.ofInteger(1));
    }

    @Override
    public CustomAttribute createVariant() {
        return CustomAttribute.of("x-custom-date", AttributeValue.ofDateTime(FIXED_TIME));
    }
}
