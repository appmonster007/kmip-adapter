package org.purplebean.kmip.codec.json.model.v2x1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.structure.PublicKeyAttributes;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PublicKeyAttributes Json Serialization Tests")
class PublicKeyAttributesJsonTest extends AbstractJsonSerializationTestSuite<PublicKeyAttributes> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<PublicKeyAttributes> type() {
    return PublicKeyAttributes.class;
  }

  @Override
  public PublicKeyAttributes createDefault() {
    return PublicKeyAttributes.of(Collections.emptyList());
  }

  @Override
  public PublicKeyAttributes createVariant() {
    return PublicKeyAttributes.of(Collections.emptyList());
  }
}
