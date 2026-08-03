package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ApplicationNamespace Domain Tests")
class ApplicationNamespaceTest extends AbstractKmipDataTypeTestSuite<ApplicationNamespace> {

  @Override
  protected Class<ApplicationNamespace> type() {
    return ApplicationNamespace.class;
  }

  @Override
  protected ApplicationNamespace createDefault() {
    return ApplicationNamespace
        .builder()
        .value("test-namespace")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}