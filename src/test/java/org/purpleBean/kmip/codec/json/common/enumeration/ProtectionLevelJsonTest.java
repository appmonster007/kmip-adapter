package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ProtectionLevel JSON Serialization")
class ProtectionLevelJsonTest extends AbstractJsonSerializationSuite<ProtectionLevel> {
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
