package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.structure.CommonAttributes;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CommonAttributes Xml Serialization Tests")
class CommonAttributesXmlTest extends AbstractXmlSerializationTestSuite<CommonAttributes> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<CommonAttributes> type() {
    return CommonAttributes.class;
  }

  @Override
  public CommonAttributes createDefault() {
    return CommonAttributes.of(Collections.emptyList());
  }

  @Override
  public CommonAttributes createVariant() {
    return CommonAttributes.of(Collections.emptyList());
  }
}
