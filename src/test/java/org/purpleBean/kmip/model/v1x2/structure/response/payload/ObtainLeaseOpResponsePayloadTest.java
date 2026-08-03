package org.purpleBean.kmip.model.v1x2.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.LastChangeDate;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ObtainLeaseOpResponsePayload Domain Tests")
class ObtainLeaseOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<ObtainLeaseOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<ObtainLeaseOpResponsePayload> type() {
    return ObtainLeaseOpResponsePayload.class;
  }

  @Override
  protected ObtainLeaseOpResponsePayload createDefault() {
    return ObtainLeaseOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .leaseTime(LeaseTime.of(3600))
        .lastChangeDate(LastChangeDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
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
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(LeaseTime.class);
    assertThat(values.get(2)).isInstanceOf(LastChangeDate.class);
  }
}
