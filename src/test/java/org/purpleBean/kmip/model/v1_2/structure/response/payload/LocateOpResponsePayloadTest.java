package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("LocateOpResponsePayload Domain Tests")
class LocateOpResponsePayloadTest extends AbstractKmipStructureTestSuite<LocateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<LocateOpResponsePayload> type() {
    return LocateOpResponsePayload.class;
  }

  @Override
  protected LocateOpResponsePayload createDefault() {
    return LocateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("12345"))
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
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
  }
}
