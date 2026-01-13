package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AdjustmentType Xml Serialization Tests")
class AdjustmentTypeXmlTest extends AbstractXmlSerializationTestSuite<AdjustmentType> {

    @Override
    protected Class<AdjustmentType> type() {
        return AdjustmentType.class;
    }

    @Override
    protected AdjustmentType createDefault() {
        return new AdjustmentType(AdjustmentType.Standard.INCREMENT);
    }

    @Override
    protected AdjustmentType createVariant() {
        return new AdjustmentType(AdjustmentType.Standard.DECREMENT);
    }
}