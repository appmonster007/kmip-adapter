package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AdjustmentType Ttlv Serialization Tests")
class AdjustmentTypeTtlvTest extends AbstractTtlvSerializationTestSuite<AdjustmentType> {

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