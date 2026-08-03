package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ContactInformation;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ContactInformation XML Serialization Tests")
class ContactInformationXmlTest extends AbstractXmlSerializationTestSuite<ContactInformation> {

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
