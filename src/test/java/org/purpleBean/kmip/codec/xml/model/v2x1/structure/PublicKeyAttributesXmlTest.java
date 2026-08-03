package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.structure.PublicKeyAttributes;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PublicKeyAttributes Xml Serialization Tests")
class PublicKeyAttributesXmlTest extends AbstractXmlSerializationTestSuite<PublicKeyAttributes> {

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
