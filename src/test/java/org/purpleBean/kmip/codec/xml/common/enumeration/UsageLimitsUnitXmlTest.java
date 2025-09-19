package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("UsageLimitsUnit XML Serialization")
class UsageLimitsUnitXmlTest extends AbstractXmlSerializationSuite<UsageLimitsUnit> {
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
