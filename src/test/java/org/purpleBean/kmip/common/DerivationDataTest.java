package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("DerivationData Domain Tests")
class DerivationDataTest extends AbstractKmipDataTypeTestSuite<DerivationData> {

    @Override
    protected Class<DerivationData> type() {
        return DerivationData.class;
    }

    @Override
    protected DerivationData createDefault() {
        return DerivationData.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}