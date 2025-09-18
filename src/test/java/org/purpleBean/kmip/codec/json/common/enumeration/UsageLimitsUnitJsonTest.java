package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("UsageLimitsUnit JSON Serialization")
class UsageLimitsUnitJsonTest extends AbstractJsonSerializationSuite<UsageLimitsUnit> {
    @Override
    protected Class<UsageLimitsUnit> type() {
        return UsageLimitsUnit.class;
    }

    @Override
    protected UsageLimitsUnit createDefault() {
        return new UsageLimitsUnit(UsageLimitsUnit.Standard.PLACEHOLDER_1);
    }

    @Override
    protected UsageLimitsUnit createVariant() {
        return new UsageLimitsUnit(UsageLimitsUnit.Standard.PLACEHOLDER_2);
    }
}
