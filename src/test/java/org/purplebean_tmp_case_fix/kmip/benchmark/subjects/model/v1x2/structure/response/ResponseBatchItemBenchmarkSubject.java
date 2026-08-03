package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseBatchItem;

public class ResponseBatchItemBenchmarkSubject extends KmipBenchmarkSubject<ResponseBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ResponseBatchItemBenchmarkSubject() throws Exception {
    KmipContext.setSpec(spec);
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
