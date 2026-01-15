package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AdjustmentType Json Serialization Tests")
class AdjustmentTypeJsonTest extends AbstractJsonSerializationTestSuite<AdjustmentType> {

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