package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ResultReason;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ResultReason TTLV Serialization")
class ResultReasonTtlvTest extends AbstractTtlvSerializationSuite<ResultReason> {
    @Override
    protected Class<ResultReason> type() {
        return ResultReason.class;
    }

    @Override
    protected ResultReason createDefault() {
        return new ResultReason(ResultReason.Standard.ITEM_NOT_FOUND);
    }

    @Override
    protected ResultReason createVariant() {
        return new ResultReason(ResultReason.Standard.RESPONSE_TOO_LARGE);
    }
}
