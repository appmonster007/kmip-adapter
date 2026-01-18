package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestHeader;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestMessage;

public class RequestMessageBenchmarkSubject extends KmipBenchmarkSubject<RequestMessage> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public RequestMessageBenchmarkSubject() throws Exception {
        RequestHeader header = RequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .batchCount(BatchCount.of(0))
                .build();
        RequestBatchItem item = RequestBatchItem.builder()
                .operation(Operation.Standard.CREATE.inst())
                .requestPayloadStructure(SimpleRequestPayload.of())
                .build();
        RequestMessage subject = RequestMessage.builder()
                .requestHeader(header)
                .requestBatchItem(item).requestBatchItemError(null)
                .build();
        initialize(subject, RequestMessage.class);
    }

    @Override
    public String name() {
        return "RequestMessage";
    }
}