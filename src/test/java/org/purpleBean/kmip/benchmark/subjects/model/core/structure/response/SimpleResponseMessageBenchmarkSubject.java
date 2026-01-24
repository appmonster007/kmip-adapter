package org.purpleBean.kmip.benchmark.subjects.model.core.structure.response;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseMessage;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

public class SimpleResponseMessageBenchmarkSubject extends KmipBenchmarkSubject<SimpleResponseMessage> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public SimpleResponseMessageBenchmarkSubject() throws Exception {
        SimpleResponseMessage subject = SimpleResponseMessage.builder()
                .responseHeader(SimpleResponseHeader.builder()
                        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
                        .build())
                .responseBatchItem(SimpleResponseBatchItem.builder()
                        .operation(Operation.of(Operation.Standard.CREATE))
                        .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
                        .responsePayloadStructure(SimpleResponsePayload.builder().build())
                        .build())
                .responseBatchItemError(null)
                .build();
        initialize(subject, SimpleResponseMessage.class);
    }

    @Override
    public String name() {
        return "SimpleResponseMessage";
    }
}
