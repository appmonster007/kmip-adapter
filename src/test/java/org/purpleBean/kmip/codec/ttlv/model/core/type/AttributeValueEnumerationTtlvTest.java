package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@Disabled
@DisplayName("AttributeValue.Enumeration TTLV Serialization Tests")
class AttributeValueEnumerationTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeValueEnumeration> {

    @Override
    protected Class<AttributeValueEnumeration> type() {
        return AttributeValueEnumeration.class;
    }

    @Override
    protected AttributeValueEnumeration createDefault() {
        return AttributeValueEnumeration.of(State.Standard.ACTIVE);
    }

    @Override
    protected AttributeValueEnumeration createVariant() {
        return AttributeValueEnumeration.of(State.Standard.ACTIVE);
    }
}
