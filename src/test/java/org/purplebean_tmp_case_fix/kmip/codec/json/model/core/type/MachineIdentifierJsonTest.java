package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.MachineIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MachineIdentifier JSON Serialization Tests")
class MachineIdentifierJsonTest extends AbstractJsonSerializationTestSuite<MachineIdentifier> {

  @Override
  public Class<MachineIdentifier> type() {
    return MachineIdentifier.class;
  }

  @Override
  public MachineIdentifier createDefault() {
    return MachineIdentifier
        .builder()
        .value("test-machine-id")
        .build();
  }

  @Override
  public MachineIdentifier createVariant() {
    return MachineIdentifier
        .builder()
        .value("another-machine-id")
        .build();
  }
}