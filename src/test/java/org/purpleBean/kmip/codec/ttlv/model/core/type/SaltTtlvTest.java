package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Salt;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Salt TTLV Serialization Tests")
class SaltTtlvTest extends AbstractTtlvSerializationTestSuite<Salt> {

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