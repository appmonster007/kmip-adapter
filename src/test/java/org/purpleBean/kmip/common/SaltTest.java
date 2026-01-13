package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Salt Domain Tests")
class SaltTest extends AbstractKmipDataTypeTestSuite<Salt> {

    @Override
    protected Class<Salt> type() {
        return Salt.class;
    }

    @Override
    protected Salt createDefault() {
        return Salt.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}