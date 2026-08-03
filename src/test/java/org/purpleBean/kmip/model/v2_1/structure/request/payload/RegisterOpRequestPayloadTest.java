package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RegisterOpRequestPayload Domain Tests")
class RegisterOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<RegisterOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<RegisterOpRequestPayload> type() {
    return RegisterOpRequestPayload.class;
  }

  @Override
  protected RegisterOpRequestPayload createDefault() {
    return RegisterOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .attributes(Attributes
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .object(SymmetricKey
            .builder()
            .keyBlock(KeyBlock
                .builder()
                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                .build())
            .build())
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 3;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(3);
    assertThat(values.get(0)).isInstanceOf(ObjectType.class);
    assertThat(values.get(1)).isInstanceOf(Attributes.class);
    assertThat(values.get(2)).isInstanceOf(ManagedObject.class);
  }
}