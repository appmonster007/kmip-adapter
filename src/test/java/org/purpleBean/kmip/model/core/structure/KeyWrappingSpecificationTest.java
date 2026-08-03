package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("KeyWrappingSpecification Domain Tests")
class KeyWrappingSpecificationTest
    extends AbstractKmipStructureTestSuite<KeyWrappingSpecification> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<KeyWrappingSpecification> type() {
    return KeyWrappingSpecification.class;
  }

  @Override
  protected KeyWrappingSpecification createDefault() {
    return KeyWrappingSpecification
        .builder()
        .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
        .encryptionKeyInformation(EncryptionKeyInformation
            .builder()
            .uniqueIdentifier(UniqueIdentifier.of(UUID
                .randomUUID()
                .toString()))
            .build())
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
    assertThat(values.get(0)).isInstanceOf(WrappingMethod.class);
    assertThat(values.get(1)).isInstanceOf(EncryptionKeyInformation.class);
  }
}