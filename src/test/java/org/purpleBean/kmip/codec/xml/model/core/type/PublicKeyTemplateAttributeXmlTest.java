package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PublicKeyTemplateAttribute Xml Serialization Tests")
class PublicKeyTemplateAttributeXmlTest
    extends AbstractXmlSerializationTestSuite<PublicKeyTemplateAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<PublicKeyTemplateAttribute> type() {
    return PublicKeyTemplateAttribute.class;
  }

  @Override
  public PublicKeyTemplateAttribute createDefault() {
    return PublicKeyTemplateAttribute.of("default-string");
  }

  @Override
  public PublicKeyTemplateAttribute createVariant() {
    return PublicKeyTemplateAttribute.of("variant-string");
  }
}