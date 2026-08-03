package org.purplebean.kmip.codec.json.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v2x1.structure.CurrentAttribute;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CurrentAttribute Json Serialization Tests")
class CurrentAttributeJsonTest extends AbstractJsonSerializationTestSuite<CurrentAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<CurrentAttribute> type() {
    return CurrentAttribute.class;
  }

  @Override
  public CurrentAttribute createDefault() {
    return CurrentAttribute
        .builder()
        .attribute(CryptographicAlgorithm.Standard.AES.inst())
        .build();
  }

  @Override
  public CurrentAttribute createVariant() {
    return CurrentAttribute
        .builder()
        .attribute(CryptographicAlgorithm.Standard.RSA.inst())
        .build();
  }
}
