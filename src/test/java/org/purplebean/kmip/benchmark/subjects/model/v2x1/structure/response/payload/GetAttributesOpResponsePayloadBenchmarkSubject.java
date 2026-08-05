package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import java.util.List;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.response.payload.GetAttributesOpResponsePayload;

/**
 * Benchmark subject for {@link GetAttributesOpResponsePayload}.
 */
public class GetAttributesOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetAttributesOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link GetAttributesOpResponsePayloadBenchmarkSubject}.
   */
  public GetAttributesOpResponsePayloadBenchmarkSubject() throws Exception {
    GetAttributesOpResponsePayload subject = GetAttributesOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .attributes(Attributes.of(List.of()))
        .build();
    initialize(subject, GetAttributesOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "GetAttributesOpResponsePayload";
  }
}
