package org.purpleBean.kmip.model.v1x2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RevokeOpRequestPayload Domain Tests")
class RevokeOpRequestPayloadTest extends AbstractKmipStructureTestSuite<RevokeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<RevokeOpRequestPayload> type() {
    return RevokeOpRequestPayload.class;
  }

  @Override
  protected RevokeOpRequestPayload createDefault() {
    return RevokeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .revocationReason(
            RevocationReason
                .builder()
                .revocationReasonCode(RevocationReasonCode.Standard.AFFILIATION_CHANGED.inst())
                .build()
        )
        .compromiseOccurrenceDate(CompromiseOccurrenceDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
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
    assertThat(values).hasSize(3);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(RevocationReason.class);
    assertThat(values.get(2)).isInstanceOf(CompromiseOccurrenceDate.class);
  }
}
