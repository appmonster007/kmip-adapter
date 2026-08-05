package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.EncryptOpResponsePayload;

/**
 * Benchmark subject for {@link EncryptOpResponsePayload}.
 */
public class EncryptOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<EncryptOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link EncryptOpResponsePayloadBenchmarkSubject}.
   */
  public EncryptOpResponsePayloadBenchmarkSubject() throws Exception {
    EncryptOpResponsePayload subject = EncryptOpResponsePayload
        .builder()
        .build();
    initialize(subject, EncryptOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "EncryptOpResponsePayload";
  }
}