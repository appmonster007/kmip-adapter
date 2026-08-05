package org.purplebean.kmip.benchmark.subjects.model.core.structure.response;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ResultReason;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purplebean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purplebean.kmip.model.core.type.ResultMessage;

/**
 * Benchmark subject for {@link SimpleResponseBatchItem}.
 */
public class SimpleResponseBatchItemBenchmarkSubject
    extends KmipBenchmarkSubject<SimpleResponseBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link SimpleResponseBatchItemBenchmarkSubject}.
   */
  public SimpleResponseBatchItemBenchmarkSubject() throws Exception {
    SimpleResponseBatchItem subject = SimpleResponseBatchItem
        .builder()
        .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
        .resultReason(ResultReason.of(ResultReason.Standard.ITEM_NOT_FOUND))
        .resultMessage(ResultMessage.of("Success"))
        .responsePayloadStructure(SimpleResponsePayload
            .builder()
            .build())
        .build();
    initialize(subject, SimpleResponseBatchItem.class);
  }

  @Override
  public String name() {
    return "SimpleResponseBatchItem";
  }
}
