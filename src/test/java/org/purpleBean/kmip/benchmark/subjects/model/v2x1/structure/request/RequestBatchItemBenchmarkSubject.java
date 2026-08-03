package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2x1.structure.request.RequestBatchItem;

public class RequestBatchItemBenchmarkSubject extends KmipBenchmarkSubject<RequestBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public RequestBatchItemBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
    RequestBatchItem subject = RequestBatchItem
        .builder()
        .operation(Operation.of(Operation.Standard.CREATE))
        .build();
    initialize(subject, RequestBatchItem.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "RequestBatchItem";
  }
}
