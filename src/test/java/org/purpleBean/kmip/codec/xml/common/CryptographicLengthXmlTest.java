package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CryptographicLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;


@DisplayName("CryptographicLength XML Serialization Tests")
class CryptographicLengthXmlTest extends AbstractXmlSerializationSuite<CryptographicLength> {

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
