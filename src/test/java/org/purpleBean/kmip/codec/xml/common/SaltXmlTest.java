package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Salt;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Salt XML Serialization Tests")
class SaltXmlTest extends AbstractXmlSerializationTestSuite<Salt> {

    @Override
    protected Class<Salt> type() {
        return Salt.class;
    }

    @Override
    protected Salt createDefault() {
        return Salt.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected Salt createVariant() {
        return Salt.of(new byte[]{0x04, 0x05, 0x06});
    }
}