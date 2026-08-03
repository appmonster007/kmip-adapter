package org.purpleBean.kmip.model.v3_0.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DeactivateOpResponsePayload Domain Tests")
class DeactivateOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<DeactivateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<DeactivateOpResponsePayload> type() {
    return DeactivateOpResponsePayload.class;
  }

  @Override
  protected DeactivateOpResponsePayload createDefault() {
    return DeactivateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    return 1;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
  }
}