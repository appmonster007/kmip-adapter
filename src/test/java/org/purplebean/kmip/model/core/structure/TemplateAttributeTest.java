package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.NameValue;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("TemplateAttribute Domain Tests")
class TemplateAttributeTest extends AbstractKmipStructureTestSuite<TemplateAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<TemplateAttribute> type() {
    return TemplateAttribute.class;
  }

  @Override
  protected TemplateAttribute createDefault() {
    return TemplateAttribute
        .builder()
        .name(Name.of(
            NameValue.of("test-name"),
            NameType.Standard.UNINTERPRETED_TEXT_STRING.inst()
        ))
        .attribute(Attribute.of(
            CustomAttribute.of("x-apple", AttributeValue.ofInteger(1))
        ))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(Name.class);
    assertThat(values.get(1)).isInstanceOf(Attribute.class);
  }
}