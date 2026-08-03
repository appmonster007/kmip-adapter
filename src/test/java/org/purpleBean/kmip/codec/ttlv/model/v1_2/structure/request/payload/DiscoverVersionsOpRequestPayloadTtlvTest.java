package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DiscoverVersionsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DiscoverVersionsOpRequestPayload Ttlv Serialization Tests")
class DiscoverVersionsOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DiscoverVersionsOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<DiscoverVersionsOpRequestPayload> type() {
    return DiscoverVersionsOpRequestPayload.class;
  }

  @Override
  public DiscoverVersionsOpRequestPayload createDefault() {
    return DiscoverVersionsOpRequestPayload
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .build();
  }

  @Override
  public DiscoverVersionsOpRequestPayload createVariant() {
    return DiscoverVersionsOpRequestPayload
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(3)))
        .build();
  }
}
