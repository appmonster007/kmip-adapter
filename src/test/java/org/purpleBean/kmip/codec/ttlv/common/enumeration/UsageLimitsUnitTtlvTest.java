package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("UsageLimitsUnit TTLV Serialization")
class UsageLimitsUnitTtlvTest extends AbstractTtlvSerializationSuite<UsageLimitsUnit> {
    @Override
    protected Class<UsageLimitsUnit> type() {
        return UsageLimitsUnit.class;
    }

    @Override
    protected UsageLimitsUnit createDefault() {
        return new UsageLimitsUnit(UsageLimitsUnit.Standard.BYTE);
    }

    @Override
    protected UsageLimitsUnit createVariant() {
        return new UsageLimitsUnit(UsageLimitsUnit.Standard.OBJECT);
    }
}
