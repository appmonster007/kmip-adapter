package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.ActivationDate;
import org.purplebean.kmip.model.core.type.AttributeIndex;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purplebean.kmip.util.StringUtils;

@DisplayName("Attribute Domain Tests")
class AttributeTest extends AbstractKmipStructureTestSuite<Attribute> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<Attribute> type() {
    return Attribute.class;
  }

  @Override
  protected Attribute createDefault() {
    ActivationDate activationDate = ActivationDate
        .builder()
        .value(FIXED_TIME)
        .build();
    return Attribute.of(activationDate);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // Add assertions for components if desired
    assertThat(values.get(0)).isInstanceOf(AttributeName.class);
    assertThat(((AttributeName) values.get(0)).getValue()).isEqualTo(
        StringUtils.convertPascalToTitleCase("Activation Date"));
    if (values.size() == 2) {
      assertThat(values.get(1)).isInstanceOf(AttributeValue.class);
    }
    if (values.size() > 2) {
      assertThat(values.get(1)).isInstanceOf(AttributeIndex.class);
      assertThat(values.get(2)).isInstanceOf(AttributeValue.class);
    }
  }
}
