package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PgpKeyVersion Xml Serialization Tests")
class PgpKeyVersionXmlTest extends AbstractXmlSerializationTestSuite<PgpKeyVersion> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<PgpKeyVersion> type() {
    return PgpKeyVersion.class;
  }

  @Override
  public PgpKeyVersion createDefault() {
    return PgpKeyVersion.of(123);
  }

  @Override
  public PgpKeyVersion createVariant() {
    return PgpKeyVersion.of(456);
  }
}