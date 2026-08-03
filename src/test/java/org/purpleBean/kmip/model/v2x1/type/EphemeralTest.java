package org.purpleBean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("Ephemeral Domain Tests")
class EphemeralTest extends AbstractKmipDataTypeTestSuite<Ephemeral> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<Ephemeral> type() {
    return Ephemeral.class;
  }

  @Override
  protected Ephemeral createDefault() {
    return Ephemeral.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}