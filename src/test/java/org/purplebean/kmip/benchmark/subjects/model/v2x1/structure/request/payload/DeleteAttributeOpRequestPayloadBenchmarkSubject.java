package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DeleteAttributeOpRequestPayload;

public class DeleteAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DeleteAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public DeleteAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    DeleteAttributeOpRequestPayload subject = DeleteAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .attributeReference(AttributeReference
            .builder()
            .attributeName(AttributeName.of("Contact Information"))
            .build())
        .build();
    initialize(subject, DeleteAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DeleteAttributeOpRequestPayload";
  }
}
