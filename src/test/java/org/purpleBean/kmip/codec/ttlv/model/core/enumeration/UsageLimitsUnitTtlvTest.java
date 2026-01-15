package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UsageLimitsUnit TTLV Serialization")
class UsageLimitsUnitTtlvTest extends AbstractTtlvSerializationTestSuite<UsageLimitsUnit> {
    @Override
    protected Class<UsageLimitsUnit> type() {
        return UsageLimitsUnit.class;
    }

    @Override
    protected UsageLimitsUnit createDefault() {
        return UsageLimitsUnit.Standard.BYTE.inst();
    }

    @Override
    protected UsageLimitsUnit createVariant() {
        return UsageLimitsUnit.Standard.OBJECT.inst();
    }
}
