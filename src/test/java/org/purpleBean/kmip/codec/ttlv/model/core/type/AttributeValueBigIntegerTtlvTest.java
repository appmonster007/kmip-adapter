package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("AttributeValue.BigInteger TTLV Serialization Tests")
class AttributeValueBigIntegerTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeValueBigInteger> {

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
