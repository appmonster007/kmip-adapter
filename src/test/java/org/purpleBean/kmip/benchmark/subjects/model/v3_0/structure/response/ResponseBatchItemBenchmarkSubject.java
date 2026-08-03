package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.response;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.structure.response.ResponseBatchItem;

public class ResponseBatchItemBenchmarkSubject extends KmipBenchmarkSubject<ResponseBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ResponseBatchItemBenchmarkSubject() throws Exception {
    ResponseBatchItem subject = ResponseBatchItem
        .builder()
        .build();
    initialize(subject, ResponseBatchItem.class);
  }

  @Override
  public String name() {
    return "ResponseBatchItem";
  }
}