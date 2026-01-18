package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;

public class RequestBatchItemBenchmarkSubject extends KmipBenchmarkSubject<RequestBatchItem> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public RequestBatchItemBenchmarkSubject() throws Exception {
        RequestBatchItem subject = RequestBatchItem.builder()
                .operation(Operation.Standard.CREATE.inst())
                .requestPayload(SimpleRequestPayload.of())
                .build();
        initialize(subject, RequestBatchItem.class);
    }

    @Override
    public String name() {
        return "RequestBatchItem";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}