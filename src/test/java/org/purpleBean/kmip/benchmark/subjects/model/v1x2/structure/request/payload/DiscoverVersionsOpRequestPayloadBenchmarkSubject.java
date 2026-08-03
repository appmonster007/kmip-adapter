package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DiscoverVersionsOpRequestPayload;

public class DiscoverVersionsOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DiscoverVersionsOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public DiscoverVersionsOpRequestPayloadBenchmarkSubject() throws Exception {
    DiscoverVersionsOpRequestPayload subject = DiscoverVersionsOpRequestPayload
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .build();
    initialize(subject, DiscoverVersionsOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DiscoverVersionsOpRequestPayload";
  }
}
