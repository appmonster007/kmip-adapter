package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResultReason TTLV Serialization")
class ResultReasonTtlvTest extends AbstractTtlvSerializationTestSuite<ResultReason> {
    @Override
    protected Class<ResultReason> type() {
        return ResultReason.class;
    }

    @Override
    protected ResultReason createDefault() {
        return ResultReason.Standard.ITEM_NOT_FOUND.inst();
    }

    @Override
    protected ResultReason createVariant() {
        return ResultReason.Standard.RESPONSE_TOO_LARGE.inst();
    }
}
