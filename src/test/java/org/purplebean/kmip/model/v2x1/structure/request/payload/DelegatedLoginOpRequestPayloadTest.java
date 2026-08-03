package org.purplebean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.v2x1.type.RequestCount;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DelegatedLoginOpRequestPayload Domain Tests")
class DelegatedLoginOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<DelegatedLoginOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<DelegatedLoginOpRequestPayload> type() {
    return DelegatedLoginOpRequestPayload.class;
  }

  @Override
  protected DelegatedLoginOpRequestPayload createDefault() {
    return DelegatedLoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(3600))
        .requestCount(RequestCount.of(10))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    // TODO: Set the expected minimum number of components
    return 0;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    // TODO: Validate the components of the structure
    // assertThat(values).hasSize(0);
  }
}