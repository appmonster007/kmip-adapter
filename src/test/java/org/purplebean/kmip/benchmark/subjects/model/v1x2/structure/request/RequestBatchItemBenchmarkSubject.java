package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.v1x2.structure.request.RequestBatchItem;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateOpRequestPayload;

/**
 * Benchmark subject for {@link RequestBatchItem}.
 */
public class RequestBatchItemBenchmarkSubject extends KmipBenchmarkSubject<RequestBatchItem> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link RequestBatchItemBenchmarkSubject}.
   */
  public RequestBatchItemBenchmarkSubject() throws Exception {
    RequestBatchItem subject = RequestBatchItem
        .builder()
        .operation(Operation.Standard.CREATE.inst())
        .requestPayloadStructure(CreateOpRequestPayload
            .builder()
            .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
            .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
            .build())
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