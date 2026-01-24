package org.purpleBean.kmip.benchmark.subjects.model.core.structure.response;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purpleBean.kmip.model.core.type.ResultMessage;

public class SimpleResponseBatchItemBenchmarkSubject extends KmipBenchmarkSubject<SimpleResponseBatchItem> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public SimpleResponseBatchItemBenchmarkSubject() throws Exception {
        SimpleResponseBatchItem subject = SimpleResponseBatchItem.builder()
                .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
                .resultReason(ResultReason.of(ResultReason.Standard.ITEM_NOT_FOUND))
                .resultMessage(ResultMessage.of("Success"))
                .responsePayloadStructure(SimpleResponsePayload.builder().build())
                .build();
        initialize(subject, SimpleResponseBatchItem.class);
    }

    @Override
    public String name() {
        return "SimpleResponseBatchItem";
    }
}
