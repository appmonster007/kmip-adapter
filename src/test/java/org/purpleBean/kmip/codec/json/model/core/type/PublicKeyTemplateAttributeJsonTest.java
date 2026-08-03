package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.PublicKeyTemplateAttribute;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PublicKeyTemplateAttribute Json Serialization Tests")
class PublicKeyTemplateAttributeJsonTest
    extends AbstractJsonSerializationTestSuite<PublicKeyTemplateAttribute> {

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