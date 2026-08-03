package org.purplebean.kmip.codec.ttlv.model.core.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseHeader;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SimpleResponseHeader Ttlv Serialization Tests")
class SimpleResponseHeaderTtlvTest
    extends AbstractTtlvSerializationTestSuite<SimpleResponseHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<SimpleResponseHeader> type() {
    return SimpleResponseHeader.class;
  }

  @Override
  public SimpleResponseHeader createDefault() {
    return SimpleResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .build();
  }

  @Override
  public SimpleResponseHeader createVariant() {
    return SimpleResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(3)))
        .build();
  }
}
