package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CheckOpRequestPayload Domain Tests")
class CheckOpRequestPayloadTest extends AbstractKmipStructureTestSuite<CheckOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<CheckOpRequestPayload> type() {
    return CheckOpRequestPayload.class;
  }

  @Override
  protected CheckOpRequestPayload createDefault() {
    return CheckOpRequestPayload
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
    return 0;
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
