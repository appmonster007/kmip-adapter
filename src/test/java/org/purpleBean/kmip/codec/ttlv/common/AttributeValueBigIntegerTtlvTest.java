package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueBigInteger;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.math.BigInteger;

@DisplayName("AttributeValue.BigInteger TTLV Serialization Tests")
class AttributeValueBigIntegerTtlvTest extends AbstractTtlvSerializationSuite<AttributeValueBigInteger> {

    @Override
    protected Class<AttributeValueBigInteger> type() {
        return AttributeValueBigInteger.class;
    }

    @Override
    protected AttributeValueBigInteger createDefault() {
        return AttributeValueBigInteger.of(BigInteger.valueOf(123));
    }

    @Override
    protected AttributeValueBigInteger createVariant() {
        return AttributeValueBigInteger.of(BigInteger.valueOf(456));
    }
}
