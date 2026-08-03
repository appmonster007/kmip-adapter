package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ApplicationNamespace XML Serialization Tests")
class ApplicationNamespaceXmlTest extends AbstractXmlSerializationTestSuite<ApplicationNamespace> {

  @Override
  public Class<ApplicationNamespace> type() {
    return ApplicationNamespace.class;
  }

  @Override
  public ApplicationNamespace createDefault() {
    return ApplicationNamespace
        .builder()
        .value("test-namespace")
        .build();
  }

  @Override
  public ApplicationNamespace createVariant() {
    return ApplicationNamespace
        .builder()
        .value("another-namespace")
        .build();
  }
}