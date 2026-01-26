package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@Disabled
@DisplayName("AttributeValue.Enumeration JSON Serialization Tests")
class AttributeValueEnumerationJsonTest extends AbstractJsonSerializationTestSuite<AttributeValueEnumeration> {

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
        return AttributeValueEnumeration.of(AdjustmentType.Standard.INCREMENT);
    }
}
