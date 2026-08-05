package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.DeleteAttributeOpResponsePayload;

/**
 * Benchmark subject for {@link DeleteAttributeOpResponsePayload}.
 */
public class DeleteAttributeOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DeleteAttributeOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link DeleteAttributeOpResponsePayloadBenchmarkSubject}.
   */
  public DeleteAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
    DeleteAttributeOpResponsePayload subject = DeleteAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, DeleteAttributeOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "DeleteAttributeOpResponsePayload";
  }
}
