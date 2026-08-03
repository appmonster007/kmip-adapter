package org.purpleBean.kmip.codec.json.model.v2_1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Attributes Json Serialization Tests")
class AttributesJsonTest extends AbstractJsonSerializationTestSuite<Attributes> {

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
