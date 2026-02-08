package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProtectionLevel TTLV Serialization")
class ProtectionLevelTtlvTest extends AbstractTtlvSerializationTestSuite<ProtectionLevel> {
    @Override
    public Class<ProtectionLevel> type() {
        return ProtectionLevel.class;
    }

    @Override
    public ProtectionLevel createDefault() {
        return ProtectionLevel.Standard.HIGH.inst();
    }

    @Override
    public ProtectionLevel createVariant() {
        return ProtectionLevel.Standard.LOW.inst();
    }
}
