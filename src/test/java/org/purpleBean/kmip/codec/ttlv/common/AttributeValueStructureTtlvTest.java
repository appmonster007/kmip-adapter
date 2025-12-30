package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.util.List;

@DisplayName("AttributeValue.Structure TTLV Serialization Tests")
class AttributeValueStructureTtlvTest extends AbstractTtlvSerializationSuite<AttributeValue.Structure> {

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
