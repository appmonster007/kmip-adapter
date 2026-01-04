package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("MACSignature Domain Tests")
class MACSignatureTest extends AbstractKmipDataTypeSuite<MACSignature> {

    @Override
    protected Class<MACSignature> type() {
        return MACSignature.class;
    }

    @Override
    protected MACSignature createDefault() {
        return MACSignature.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}