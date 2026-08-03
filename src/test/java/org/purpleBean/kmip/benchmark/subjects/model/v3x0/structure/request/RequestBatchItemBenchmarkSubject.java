package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.structure.request.RequestBatchItem;

public class RequestBatchItemBenchmarkSubject extends KmipBenchmarkSubject<RequestBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public RequestBatchItemBenchmarkSubject() throws Exception {
    RequestBatchItem subject = RequestBatchItem
        .builder()
        .build();
    initialize(subject, RequestBatchItem.class);
  }

  @Override
  public String name() {
    return "RequestBatchItem";
  }
}