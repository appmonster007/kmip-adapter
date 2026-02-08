package org.purpleBean.kmip.codec.ttlv.model.core.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purpleBean.kmip.model.core.type.ResultMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SimpleResponseBatchItem Ttlv Serialization Tests")
class SimpleResponseBatchItemTtlvTest extends AbstractTtlvSerializationTestSuite<SimpleResponseBatchItem> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<SimpleResponseBatchItem> type() {
        return SimpleResponseBatchItem.class;
    }

    @Override
    public SimpleResponseBatchItem createDefault() {
        return SimpleResponseBatchItem.builder()
                .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
                .resultReason(ResultReason.of(ResultReason.Standard.ITEM_NOT_FOUND))
                .resultMessage(ResultMessage.of("Success"))
                .responsePayloadStructure(SimpleResponsePayload.builder().build())
                .build();
    }

    @Override
    public SimpleResponseBatchItem createVariant() {
        return SimpleResponseBatchItem.builder()
                .resultStatus(ResultStatus.of(ResultStatus.Standard.OPERATION_FAILED))
                .resultReason(ResultReason.of(ResultReason.Standard.PERMISSION_DENIED))
                .resultMessage(ResultMessage.of("Failure"))
                .responsePayloadStructure(SimpleResponsePayload.builder().build())
                .build();
    }
}
