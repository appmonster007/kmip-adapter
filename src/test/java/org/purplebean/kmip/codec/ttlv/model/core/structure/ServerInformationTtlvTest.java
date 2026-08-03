package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ServerInformation;
import org.purplebean.kmip.model.core.type.NameValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ServerInformation Ttlv Serialization Tests")
class ServerInformationTtlvTest extends AbstractTtlvSerializationTestSuite<ServerInformation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ServerInformation> type() {
    return ServerInformation.class;
  }

  @Override
  public ServerInformation createDefault() {
    return ServerInformation
        .builder()
        .value(NameValue.of("Test Server"))
        .build();
  }

  @Override
  public ServerInformation createVariant() {
    return ServerInformation
        .builder()
        .value(NameValue.of("Variant Server"))
        .build();
  }
}
