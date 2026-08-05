package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SignOpRequestPayload;

/**
 * Benchmark subject for {@link SignOpRequestPayload}.
 */
public class SignOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link SignOpRequestPayloadBenchmarkSubject}.
   */
  public SignOpRequestPayloadBenchmarkSubject() throws Exception {
    SignOpRequestPayload subject = SignOpRequestPayload
        .builder()
        .build();
    initialize(subject, SignOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SignOpRequestPayload";
  }
}