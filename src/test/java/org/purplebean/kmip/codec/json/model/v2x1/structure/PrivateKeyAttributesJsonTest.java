package org.purplebean.kmip.codec.json.model.v2x1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.structure.PrivateKeyAttributes;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PrivateKeyAttributes Json Serialization Tests")
class PrivateKeyAttributesJsonTest
    extends AbstractJsonSerializationTestSuite<PrivateKeyAttributes> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<PrivateKeyAttributes> type() {
    return PrivateKeyAttributes.class;
  }

  @Override
  public PrivateKeyAttributes createDefault() {
    return PrivateKeyAttributes.of(Collections.emptyList());
  }

  @Override
  public PrivateKeyAttributes createVariant() {
    return PrivateKeyAttributes.of(Collections.emptyList());
  }
}
