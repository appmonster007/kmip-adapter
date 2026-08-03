package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("AddAttributeOpRequestPayload Domain Tests")
class AddAttributeOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<AddAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<AddAttributeOpRequestPayload> type() {
    return AddAttributeOpRequestPayload.class;
  }

  @Override
  protected AddAttributeOpRequestPayload createDefault() {
    return AddAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 1;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(NewAttribute.class);
  }
}