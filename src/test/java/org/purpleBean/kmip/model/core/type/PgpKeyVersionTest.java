package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PgpKeyVersion Domain Tests")
class PgpKeyVersionTest extends AbstractKmipDataTypeTestSuite<PgpKeyVersion> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<PgpKeyVersion> type() {
    return PgpKeyVersion.class;
  }

  @Override
  protected PgpKeyVersion createDefault() {
    return PgpKeyVersion.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}