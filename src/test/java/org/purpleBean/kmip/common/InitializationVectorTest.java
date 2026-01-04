package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("InitializationVector Domain Tests")
class InitializationVectorTest extends AbstractKmipDataTypeSuite<InitializationVector> {

    @Override
    protected Class<InitializationVector> type() {
        return InitializationVector.class;
    }

    @Override
    protected InitializationVector createDefault() {
        return InitializationVector.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}