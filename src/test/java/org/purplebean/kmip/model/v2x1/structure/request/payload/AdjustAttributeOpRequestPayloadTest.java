package org.purplebean.kmip.model.v2x1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purplebean.kmip.model.v2x1.structure.CurrentAttribute;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("AdjustAttributeOpRequestPayload Domain Tests")
class AdjustAttributeOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<AdjustAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AdjustAttributeOpRequestPayload> type() {
    return AdjustAttributeOpRequestPayload.class;
  }

  @Override
  protected AdjustAttributeOpRequestPayload createDefault() {
    return AdjustAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-uid-1")
            .build())
        .currentAttribute(CurrentAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .adjustmentType(AdjustmentType.Standard.INCREMENT.inst())
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
    assertThat(values).hasSizeGreaterThanOrEqualTo(2);
    assertThat(values).anyMatch(v -> v instanceof CurrentAttribute);
    assertThat(values).anyMatch(v -> v instanceof AdjustmentType);
  }
}
