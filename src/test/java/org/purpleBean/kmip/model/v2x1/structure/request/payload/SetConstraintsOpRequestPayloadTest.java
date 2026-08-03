package org.purpleBean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2x1.structure.Constraints;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SetConstraintsOpRequestPayload Domain Tests")
class SetConstraintsOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<SetConstraintsOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<SetConstraintsOpRequestPayload> type() {
    return SetConstraintsOpRequestPayload.class;
  }

  @Override
  protected SetConstraintsOpRequestPayload createDefault() {
    return SetConstraintsOpRequestPayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
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