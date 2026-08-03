package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DiscoverVersionsOpResponsePayload;

public class DiscoverVersionsOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DiscoverVersionsOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

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
