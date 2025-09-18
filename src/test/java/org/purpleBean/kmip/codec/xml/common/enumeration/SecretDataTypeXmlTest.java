package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.SecretDataType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("SecretDataType XML Serialization")
class SecretDataTypeXmlTest extends AbstractXmlSerializationSuite<SecretDataType> {
    @Override
    protected Class<SecretDataType> type() {
        return SecretDataType.class;
    }

    @Override
    protected SecretDataType createDefault() {
        return new SecretDataType(SecretDataType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected SecretDataType createVariant() {
        return new SecretDataType(SecretDataType.Standard.PLACEHOLDER_2);
    }
}
