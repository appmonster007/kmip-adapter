package org.purplebean.kmip.model.v2x1.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ObjectDefaults Domain Tests")
class ObjectDefaultsTest extends AbstractKmipStructureTestSuite<ObjectDefaults> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<ObjectDefaults> type() {
    return ObjectDefaults.class;
  }

  @Override
  protected ObjectDefaults createDefault() {
    return ObjectDefaults
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
        .attributes(Attributes.of(Collections.emptyList()))
        .build();
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
    assertThat(values).hasSize(2);
  }
}
