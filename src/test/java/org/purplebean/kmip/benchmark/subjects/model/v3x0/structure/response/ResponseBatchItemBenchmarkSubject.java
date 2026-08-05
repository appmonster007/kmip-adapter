package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.response;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.structure.response.ResponseBatchItem;

/**
 * Benchmark subject for {@link ResponseBatchItem}.
 */
public class ResponseBatchItemBenchmarkSubject extends KmipBenchmarkSubject<ResponseBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ResponseBatchItemBenchmarkSubject}.
   */
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