package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PublicKeyTemplateAttribute Domain Tests")
class PublicKeyTemplateAttributeTest
    extends AbstractKmipDataTypeTestSuite<PublicKeyTemplateAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<PublicKeyTemplateAttribute> type() {
    return PublicKeyTemplateAttribute.class;
  }

  @Override
  protected PublicKeyTemplateAttribute createDefault() {
    return PublicKeyTemplateAttribute.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}