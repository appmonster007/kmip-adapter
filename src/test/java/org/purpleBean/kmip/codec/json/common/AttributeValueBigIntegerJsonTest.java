package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;

@DisplayName("AttributeValue.BigInteger JSON Serialization Tests")
class AttributeValueBigIntegerJsonTest extends AbstractJsonSerializationSuite<AttributeValue.BigInteger> {

    @Override
    protected Class<AttributeValue.BigInteger> type() {
        return AttributeValue.BigInteger.class;
    }

    @Override
    protected AttributeValue.BigInteger createDefault() {
        return AttributeValue.BigInteger.of(BigInteger.valueOf(123));
    }

    @Override
    protected AttributeValue.BigInteger createVariant() {
        return AttributeValue.BigInteger.of(BigInteger.valueOf(456));
    }
}
