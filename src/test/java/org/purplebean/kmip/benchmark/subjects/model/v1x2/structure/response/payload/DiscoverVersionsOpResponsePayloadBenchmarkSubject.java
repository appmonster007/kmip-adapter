package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DiscoverVersionsOpResponsePayload;

/**
 * Benchmark subject for {@link DiscoverVersionsOpResponsePayload}.
 */
public class DiscoverVersionsOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DiscoverVersionsOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link DiscoverVersionsOpResponsePayloadBenchmarkSubject}.
   */
  public DiscoverVersionsOpResponsePayloadBenchmarkSubject() throws Exception {
    DiscoverVersionsOpResponsePayload subject = DiscoverVersionsOpResponsePayload
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .build();
    initialize(subject, DiscoverVersionsOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "DiscoverVersionsOpResponsePayload";
  }
}
