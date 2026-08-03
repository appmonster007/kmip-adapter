package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.model.core.structure.Name;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.NameValue;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TemplateAttribute Xml Serialization Tests")
class TemplateAttributeXmlTest extends AbstractXmlSerializationTestSuite<TemplateAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<TemplateAttribute> type() {
    return TemplateAttribute.class;
  }

  @Override
  public TemplateAttribute createDefault() {
    return TemplateAttribute
        .builder()
        .name(Name.of(
            NameValue.of("test-name"),
            NameType.Standard.UNINTERPRETED_TEXT_STRING.inst()
        ))
        .build();
  }

  @Override
  public TemplateAttribute createVariant() {
    return TemplateAttribute
        .builder()
        .name(Name.of(
            NameValue.of("test-name-2"),
            NameType.Standard.UNINTERPRETED_TEXT_STRING.inst()
        ))
        .build();
  }
}