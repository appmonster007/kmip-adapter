package org.purpleBean.kmip.model.v2x1.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.NewAttribute;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("AdjustAttributeOpResponsePayload Domain Tests")
class AdjustAttributeOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<AdjustAttributeOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AdjustAttributeOpResponsePayload> type() {
    return AdjustAttributeOpResponsePayload.class;
  }

  @Override
  protected AdjustAttributeOpResponsePayload createDefault() {
    return AdjustAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-resp-uid-1")
            .build())
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
  public int expectedMinComponentCount() {
    return 2;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values).anyMatch(v -> v instanceof UniqueIdentifier);
    assertThat(values).anyMatch(v -> v instanceof NewAttribute);
  }
}
