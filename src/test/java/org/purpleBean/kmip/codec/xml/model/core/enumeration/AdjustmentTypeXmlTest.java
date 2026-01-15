package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AdjustmentType Xml Serialization Tests")
class AdjustmentTypeXmlTest extends AbstractXmlSerializationTestSuite<AdjustmentType> {

    @Override
    protected Class<AdjustmentType> type() {
        return AdjustmentType.class;
    }

    @Override
    protected AdjustmentType createDefault() {
        return AdjustmentType.Standard.INCREMENT.inst();
    }

    @Override
    protected AdjustmentType createVariant() {
        return AdjustmentType.Standard.DECREMENT.inst();
    }
}