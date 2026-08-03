package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("OpaqueObject Domain Tests")
class OpaqueObjectTest extends AbstractKmipStructureTestSuite<OpaqueObject> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<OpaqueObject> type() {
    return OpaqueObject.class;
  }

  @Override
  protected OpaqueObject createDefault() {
    return OpaqueObject
        .builder()
        .opaqueDataType(OpaqueDataType
            .register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion))
            .inst())
        .opaqueDataValue(OpaqueDataValue.of(new byte[0]))
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
    assertThat(values.get(0)).isInstanceOf(OpaqueDataType.class);
    assertThat(values.get(1)).isInstanceOf(OpaqueDataValue.class);
  }
}