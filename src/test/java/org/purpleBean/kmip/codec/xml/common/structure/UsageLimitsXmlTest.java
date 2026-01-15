package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.common.structure.UsageLimits;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("UsageLimits Xml Serialization Tests")
class UsageLimitsXmlTest extends AbstractXmlSerializationTestSuite<UsageLimits> {

    @Override
    protected Class<UsageLimits> type() {
        return UsageLimits.class;
    }

    @Override
    protected UsageLimits createDefault() {
        return UsageLimits.builder()
                .usageLimitsTotal(UsageLimitsTotal.of(100L))
                .usageLimitsCount(UsageLimitsCount.of(10L))
                .usageLimitsUnit(UsageLimitsUnit.Standard.BYTE.inst())
                .build();
    }
}
