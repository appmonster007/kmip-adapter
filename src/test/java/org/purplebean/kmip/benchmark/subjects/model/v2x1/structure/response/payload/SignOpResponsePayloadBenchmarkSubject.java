package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SignOpResponsePayload;

/**
 * Benchmark subject for {@link SignOpResponsePayload}.
 */
public class SignOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link SignOpResponsePayloadBenchmarkSubject}.
   */
  public SignOpResponsePayloadBenchmarkSubject() throws Exception {
    SignOpResponsePayload subject = SignOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, SignOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SignOpResponsePayload";
  }
}