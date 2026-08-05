package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SignatureVerifyOpRequestPayload;

/**
 * Benchmark subject for {@link SignatureVerifyOpRequestPayload}.
 */
public class SignatureVerifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignatureVerifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link SignatureVerifyOpRequestPayloadBenchmarkSubject}.
   */
  public SignatureVerifyOpRequestPayloadBenchmarkSubject() throws Exception {
    SignatureVerifyOpRequestPayload subject = SignatureVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, SignatureVerifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SignatureVerifyOpRequestPayload";
  }
}