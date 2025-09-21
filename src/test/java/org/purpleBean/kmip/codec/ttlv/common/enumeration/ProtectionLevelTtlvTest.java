package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ProtectionLevel TTLV Serialization")
class ProtectionLevelTtlvTest extends AbstractTtlvSerializationSuite<ProtectionLevel> {
    @Override
    protected Class<ProtectionLevel> type() {
        return ProtectionLevel.class;
    }

    @Override
    protected ProtectionLevel createDefault() {
        return new ProtectionLevel(ProtectionLevel.Standard.HIGH);
    }

    @Override
    protected ProtectionLevel createVariant() {
        return new ProtectionLevel(ProtectionLevel.Standard.LOW);
    }
}
