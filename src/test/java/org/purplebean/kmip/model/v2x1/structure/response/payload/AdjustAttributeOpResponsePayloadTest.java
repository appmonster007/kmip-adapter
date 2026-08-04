package org.purplebean.kmip.model.v2x1.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

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
    assertThat(values).anyMatch(v -> v instanceof UniqueIdentifier);
  }
}
