package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MACSignature;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MACSignature XML Serialization Tests")
class MACSignatureXmlTest extends AbstractXmlSerializationTestSuite<MACSignature> {

    @Override
    protected Class<MACSignature> type() {
        return MACSignature.class;
    }

    @Override
    protected MACSignature createDefault() {
        return MACSignature.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected MACSignature createVariant() {
        return MACSignature.of(new byte[]{0x04, 0x05, 0x06});
    }
}