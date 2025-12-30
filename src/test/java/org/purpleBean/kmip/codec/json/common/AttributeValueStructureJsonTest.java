package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.util.List;

@DisplayName("AttributeValue.Structure JSON Serialization Tests")
class AttributeValueStructureJsonTest extends AbstractJsonSerializationSuite<AttributeValue.Structure> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<AttributeValue.Structure> type() {
        return AttributeValue.Structure.class;
    }

    @Override
    protected AttributeValue.Structure createDefault() {
        return AttributeValue.Structure.of(List.of(AttributeValue.Integer.of(123)));
    }

    @Override
    protected AttributeValue.Structure createVariant() {
        return AttributeValue.Structure.of(List.of(AttributeValue.LongInteger.of(456L)));
    }
}
