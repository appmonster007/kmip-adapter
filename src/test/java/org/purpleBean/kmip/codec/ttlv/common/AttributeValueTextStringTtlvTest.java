package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueTextString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.TextString TTLV Serialization Tests")
class AttributeValueTextStringTtlvTest extends AbstractTtlvSerializationSuite<AttributeValueTextString> {

    @Override
    protected Class<AttributeValueTextString> type() {
        return AttributeValueTextString.class;
    }

    @Override
    protected AttributeValueTextString createDefault() {
        return AttributeValueTextString.of("test");
    }

    @Override
    protected AttributeValueTextString createVariant() {
        return AttributeValueTextString.of("variant");
    }
}
