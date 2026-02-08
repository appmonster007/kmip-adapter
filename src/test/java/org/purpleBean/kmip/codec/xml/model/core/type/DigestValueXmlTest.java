package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DigestValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DigestValue XML Serialization Tests")
class DigestValueXmlTest extends AbstractXmlSerializationTestSuite<DigestValue> {

    @Override
    public Class<DigestValue> type() {
        return DigestValue.class;
    }

    @Override
    public DigestValue createDefault() {
        return DigestValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public DigestValue createVariant() {
        return DigestValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}