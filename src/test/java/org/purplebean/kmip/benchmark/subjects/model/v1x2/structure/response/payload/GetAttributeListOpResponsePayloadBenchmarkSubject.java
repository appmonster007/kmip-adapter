package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetAttributeListOpResponsePayload;

/**
 * Benchmark subject for {@link GetAttributeListOpResponsePayload}.
 */
public class GetAttributeListOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetAttributeListOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link GetAttributeListOpResponsePayloadBenchmarkSubject}.
   */
  public GetAttributeListOpResponsePayloadBenchmarkSubject() throws Exception {
    GetAttributeListOpResponsePayload subject = GetAttributeListOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attributeName(AttributeName.of("Attribute1"))
        .attributeName(AttributeName.of("Attribute2"))
        .build();
    initialize(subject, GetAttributeListOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "GetAttributeListOpResponsePayload";
  }
}
