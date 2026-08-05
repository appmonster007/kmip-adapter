package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v2x1.structure.request.RequestBatchItem;

/**
 * Benchmark subject for {@link RequestBatchItem}.
 */
public class RequestBatchItemBenchmarkSubject extends KmipBenchmarkSubject<RequestBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link RequestBatchItemBenchmarkSubject}.
   */
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
