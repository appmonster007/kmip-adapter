package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AdjustmentType Xml Serialization Tests")
class AdjustmentTypeXmlTest extends AbstractXmlSerializationTestSuite<AdjustmentType> {

    @Override
    public Class<AdjustmentType> type() {
        return AdjustmentType.class;
    }

    @Override
    public AdjustmentType createDefault() {
        return AdjustmentType.Standard.INCREMENT.inst();
    }

    @Override
    public AdjustmentType createVariant() {
        return AdjustmentType.Standard.DECREMENT.inst();
    }
}