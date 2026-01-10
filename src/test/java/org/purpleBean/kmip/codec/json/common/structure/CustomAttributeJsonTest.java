package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.AttributeValueDateTime;
import org.purpleBean.kmip.common.AttributeValueEnumeration;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CustomAttribute JSON Serialization Tests")
class CustomAttributeJsonTest extends AbstractJsonSerializationSuite<CustomAttribute> {

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
        return CustomAttribute.of("x-custom-state", AttributeValueEnumeration.of(State.Standard.ACTIVE.getValue()));
    }

    @Override
    protected CustomAttribute createVariant() {
        return CustomAttribute.of("x-custom-date", AttributeValueDateTime.of(FIXED_TIME));
    }
}
