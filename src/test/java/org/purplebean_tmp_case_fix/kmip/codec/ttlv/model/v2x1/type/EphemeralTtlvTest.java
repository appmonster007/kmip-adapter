package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Ephemeral;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Ephemeral Ttlv Serialization Tests")
class EphemeralTtlvTest extends AbstractTtlvSerializationTestSuite<Ephemeral> {

  @Override
  public Class<Ephemeral> type() {
    return Ephemeral.class;
  }

  @Override
  public Ephemeral createDefault() {
    return Ephemeral.of(true);
  }

  @Override
  public Ephemeral createVariant() {
    return Ephemeral.of(false);
  }
}