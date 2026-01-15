package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CryptographicLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;


@DisplayName("CryptographicLength XML Serialization Tests")
class CryptographicLengthXmlTest extends AbstractXmlSerializationTestSuite<CryptographicLength> {

    @Override
    protected Class<CryptographicLength> type() {
        return CryptographicLength.class;
    }

    @Override
    protected CryptographicLength createDefault() {
        return CryptographicLength.of(256);
    }

    @Override
    protected CryptographicLength createVariant() {
        return CryptographicLength.of(512);
    }
}
