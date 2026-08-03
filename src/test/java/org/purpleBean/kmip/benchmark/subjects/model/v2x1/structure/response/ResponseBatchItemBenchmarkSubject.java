package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.v2x1.structure.response.ResponseBatchItem;

public class ResponseBatchItemBenchmarkSubject extends KmipBenchmarkSubject<ResponseBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ResponseBatchItemBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
    ResponseBatchItem subject = ResponseBatchItem
        .builder()
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
