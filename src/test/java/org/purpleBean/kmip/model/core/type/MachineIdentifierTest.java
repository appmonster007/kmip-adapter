package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("MachineIdentifier Domain Tests")
class MachineIdentifierTest extends AbstractKmipDataTypeTestSuite<MachineIdentifier> {

  @Override
  protected Class<MachineIdentifier> type() {
    return MachineIdentifier.class;
  }

  @Override
  protected MachineIdentifier createDefault() {
    return MachineIdentifier
        .builder()
        .value("test-machine-id")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}