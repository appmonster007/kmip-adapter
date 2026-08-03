package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UniqueIdentifier JSON Serialization Tests")
class UniqueIdentifierJsonTest extends AbstractJsonSerializationTestSuite<UniqueIdentifier> {

  @Override
  public Class<UniqueIdentifier> type() {
    return UniqueIdentifier.class;
  }

  @Override
  public UniqueIdentifier createDefault() {
    return UniqueIdentifier
        .builder()
        .value("FIXED_STRING")
        .build();
  }

  @Override
  public UniqueIdentifier createVariant() {
    return UniqueIdentifier
        .builder()
        .value("VARIANT_STRING")
        .build();
  }
}
