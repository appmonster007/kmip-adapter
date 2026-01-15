package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UsageLimitsUnit JSON Serialization")
class UsageLimitsUnitJsonTest extends AbstractJsonSerializationTestSuite<UsageLimitsUnit> {
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
