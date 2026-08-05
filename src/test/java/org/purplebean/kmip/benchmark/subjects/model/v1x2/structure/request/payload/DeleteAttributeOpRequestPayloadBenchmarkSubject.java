package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttributeIndex;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DeleteAttributeOpRequestPayload;

/**
 * Benchmark subject for {@link DeleteAttributeOpRequestPayload}.
 */
public class DeleteAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DeleteAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link DeleteAttributeOpRequestPayloadBenchmarkSubject}.
   */
  public DeleteAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    DeleteAttributeOpRequestPayload subject = DeleteAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attributeName(AttributeName.of("test-attribute"))
        .attributeIndex(AttributeIndex.of(1))
        .build();
    initialize(subject, DeleteAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DeleteAttributeOpRequestPayload";
  }
}
