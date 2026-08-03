package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ContactInformation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ContactInformation JSON Serialization Tests")
class ContactInformationJsonTest extends AbstractJsonSerializationTestSuite<ContactInformation> {


  @Override
  public Class<ContactInformation> type() {
    return ContactInformation.class;
  }

  @Override
  public ContactInformation createDefault() {
    return ContactInformation
        .builder()
        .value("test")
        .build();
  }

  @Override
  public ContactInformation createVariant() {
    return ContactInformation
        .builder()
        .value("test-2")
        .build();
  }
}
