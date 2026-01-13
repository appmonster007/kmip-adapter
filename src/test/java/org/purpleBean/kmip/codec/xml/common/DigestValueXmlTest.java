package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DigestValue XML Serialization Tests")
class DigestValueXmlTest extends AbstractXmlSerializationTestSuite<DigestValue> {

    @Override
    protected Class<DigestValue> type() {
        return DigestValue.class;
    }

    @Override
    protected DigestValue createDefault() {
        return DigestValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected DigestValue createVariant() {
        return DigestValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}