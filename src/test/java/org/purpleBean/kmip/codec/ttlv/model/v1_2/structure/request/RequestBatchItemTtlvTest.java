package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestBatchItem Ttlv Serialization Tests")
class RequestBatchItemTtlvTest extends AbstractTtlvSerializationTestSuite<RequestBatchItem> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<RequestBatchItem> type() {
        return RequestBatchItem.class;
    }

    @Override
    protected RequestBatchItem createDefault() {
        return RequestBatchItem.builder()
                .operation(Operation.Standard.CREATE.inst())
                .requestPayload(SimpleRequestPayload.of())
                .build();
    }

    @Override
    protected RequestBatchItem createVariant() {
        return RequestBatchItem.builder()
                .operation(Operation.Standard.GET.inst())
                .requestPayload(SimpleRequestPayload.of())
                .build();
    }
}