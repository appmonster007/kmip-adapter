package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("LocateOpResponsePayload Domain Tests")
class LocateOpResponsePayloadTest extends AbstractKmipStructureTestSuite<LocateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<LocateOpResponsePayload> type() {
    return LocateOpResponsePayload.class;
  }

  @Override
  protected LocateOpResponsePayload createDefault() {
    return LocateOpResponsePayload
        .builder()
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
    // all fields optional
  }
}
