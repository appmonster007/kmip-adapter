package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SubjectDistinguishedName JSON Serialization Tests")
class SubjectDistinguishedNameJsonTest
    extends AbstractJsonSerializationTestSuite<SubjectDistinguishedName> {

  @Override
  public Class<SubjectDistinguishedName> type() {
    return SubjectDistinguishedName.class;
  }

  @Override
  public SubjectDistinguishedName createDefault() {
    return SubjectDistinguishedName.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public SubjectDistinguishedName createVariant() {
    return SubjectDistinguishedName.of(new byte[] {0x04, 0x05, 0x06});
  }
}