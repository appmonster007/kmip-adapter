package org.purplebean.kmip.model.v1x2.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CheckOpResponsePayload Domain Tests")
class CheckOpResponsePayloadTest extends AbstractKmipStructureTestSuite<CheckOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<CheckOpResponsePayload> type() {
    return CheckOpResponsePayload.class;
  }

  @Override
  protected CheckOpResponsePayload createDefault() {
    return CheckOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .usageLimitsCount(UsageLimitsCount.of(100L))
        .cryptographicUsageMask(CryptographicUsageMask.of(1))
        .leaseTime(LeaseTime.of(3600))
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
    assertThat(values).hasSize(4);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(UsageLimitsCount.class);
    assertThat(values.get(2)).isInstanceOf(CryptographicUsageMask.class);
    assertThat(values.get(3)).isInstanceOf(LeaseTime.class);
  }
}
