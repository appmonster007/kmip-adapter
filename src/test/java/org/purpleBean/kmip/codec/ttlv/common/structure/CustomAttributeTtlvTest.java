package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CustomAttribute TTLV Serialization Tests")
class CustomAttributeTtlvTest extends AbstractTtlvSerializationSuite<CustomAttribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CustomAttribute> type() {
        return CustomAttribute.class;
    }

    @Override
    protected CustomAttribute createDefault() {
        return CustomAttribute.of("x-custom-state", AttributeValue.Enumeration.of(State.Standard.ACTIVE.getValue()));
    }

    @Override
    protected CustomAttribute createVariant() {
        return CustomAttribute.of("x-custom-date", AttributeValue.DateTime.of(FIXED_TIME));
    }
}
