package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.SecretDataType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SecretDataType XML Serialization")
class SecretDataTypeXmlTest extends AbstractXmlSerializationTestSuite<SecretDataType> {
    @Override
    protected Class<SecretDataType> type() {
        return SecretDataType.class;
    }

    @Override
    protected SecretDataType createDefault() {
        return new SecretDataType(SecretDataType.Standard.PASSWORD);
    }

    @Override
    protected SecretDataType createVariant() {
        return new SecretDataType(SecretDataType.Standard.SEED);
    }
}
