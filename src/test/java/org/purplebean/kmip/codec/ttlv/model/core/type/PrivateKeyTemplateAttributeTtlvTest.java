package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrivateKeyTemplateAttribute Ttlv Serialization Tests")
class PrivateKeyTemplateAttributeTtlvTest
    extends AbstractTtlvSerializationTestSuite<PrivateKeyTemplateAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<PrivateKeyTemplateAttribute> type() {
    return PrivateKeyTemplateAttribute.class;
  }

  @Override
  public PrivateKeyTemplateAttribute createDefault() {
    return PrivateKeyTemplateAttribute.of("default-string");
  }

  @Override
  public PrivateKeyTemplateAttribute createVariant() {
    return PrivateKeyTemplateAttribute.of("variant-string");
  }
}