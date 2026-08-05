package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.MacVerifyOpResponsePayload;

/**
 * Benchmark subject for {@link MacVerifyOpResponsePayload}.
 */
public class MacVerifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<MacVerifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link MacVerifyOpResponsePayloadBenchmarkSubject}.
   */
  public MacVerifyOpResponsePayloadBenchmarkSubject() throws Exception {
    MacVerifyOpResponsePayload subject = MacVerifyOpResponsePayload
        .builder()
        .build();
    initialize(subject, MacVerifyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "MacVerifyOpResponsePayload";
  }
}