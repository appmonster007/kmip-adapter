package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.EncryptOpRequestPayload;

/**
 * Benchmark subject for {@link EncryptOpRequestPayload}.
 */
public class EncryptOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<EncryptOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link EncryptOpRequestPayloadBenchmarkSubject}.
   */
  public EncryptOpRequestPayloadBenchmarkSubject() throws Exception {
    EncryptOpRequestPayload subject = EncryptOpRequestPayload
        .builder()
        .build();
    initialize(subject, EncryptOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "EncryptOpRequestPayload";
  }
}