package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("QueryAsynchronousRequestsOpResponsePayload Domain Tests")
class QueryAsynchronousRequestsOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<QueryAsynchronousRequestsOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<QueryAsynchronousRequestsOpResponsePayload> type() {
    return QueryAsynchronousRequestsOpResponsePayload.class;
  }

  @Override
  protected QueryAsynchronousRequestsOpResponsePayload createDefault() {
    // TODO: Create a default instance of the structure
    return QueryAsynchronousRequestsOpResponsePayload
        .builder()
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