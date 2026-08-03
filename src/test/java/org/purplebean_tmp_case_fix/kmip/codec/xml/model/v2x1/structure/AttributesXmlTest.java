package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Attributes Xml Serialization Tests")
class AttributesXmlTest extends AbstractXmlSerializationTestSuite<Attributes> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<Attributes> type() {
    return Attributes.class;
  }

  @Override
  public Attributes createDefault() {
    return Attributes.of(Collections.emptyList());
  }

  @Override
  public Attributes createVariant() {
    return Attributes.of(Collections.emptyList());
  }
}
