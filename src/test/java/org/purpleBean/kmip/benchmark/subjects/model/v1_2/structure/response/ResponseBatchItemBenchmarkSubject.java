package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseBatchItem;

public class ResponseBatchItemBenchmarkSubject extends KmipBenchmarkSubject<ResponseBatchItem> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ResponseBatchItemBenchmarkSubject() throws Exception {
        KmipContext.setSpec(spec);
        ResponseBatchItem subject = ResponseBatchItem.builder()
                .operation(Operation.of(Operation.Standard.CREATE))
                .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
                .build();
        initialize(subject, ResponseBatchItem.class);
        KmipContext.clear();
    }

    @Override
    public String name() {
        return "ResponseBatchItem";
    }
}
