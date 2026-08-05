package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DecryptOpRequestPayload;

/**
 * Benchmark subject for {@link DecryptOpRequestPayload}.
 */
public class DecryptOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DecryptOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link DecryptOpRequestPayloadBenchmarkSubject}.
   */
  public DecryptOpRequestPayloadBenchmarkSubject() throws Exception {
    DecryptOpRequestPayload subject = DecryptOpRequestPayload
        .builder()
        .build();
    initialize(subject, DecryptOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DecryptOpRequestPayload";
  }
}