package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestHeader;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RequestMessage Xml Serialization Tests")
class RequestMessageXmlTest extends AbstractXmlSerializationTestSuite<RequestMessage> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<RequestMessage> type() {
        return RequestMessage.class;
    }

    @Override
    protected RequestMessage createDefault() {
        RequestHeader header = RequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .batchCount(BatchCount.of(0))
                .build();
        RequestBatchItem item = RequestBatchItem.builder()
                .operation(Operation.Standard.CREATE.inst())
                .requestPayloadStructure(SimpleRequestPayload.of())
                .build();
        return RequestMessage.builder()
                .requestHeader(header)
                .requestBatchItem(item).requestBatchItemError(null)
                .build();
    }

    @Override
    protected RequestMessage createVariant() {
        RequestHeader header = RequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .batchCount(BatchCount.of(0))
                .build();
        RequestBatchItem item = RequestBatchItem.builder()
                .operation(Operation.Standard.GET.inst())
                .requestPayloadStructure(SimpleRequestPayload.of())
                .build();
        return RequestMessage.builder()
                .requestHeader(header)
                .requestBatchItem(item).requestBatchItemError(null)
                .build();
    }
}