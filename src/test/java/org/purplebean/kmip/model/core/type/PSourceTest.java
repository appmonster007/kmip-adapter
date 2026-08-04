package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PSource Domain Tests")
class PSourceTest extends AbstractKmipDataTypeTestSuite<PSource> {

  @Override
  protected Class<PSource> type() {
    return PSource.class;
  }

  @Override
  protected PSource createDefault() {
    return PSource.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}
