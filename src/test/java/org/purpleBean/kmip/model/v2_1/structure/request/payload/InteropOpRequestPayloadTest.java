package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.enumeration.InteropFunction;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("InteropOpRequestPayload Domain Tests")
class InteropOpRequestPayloadTest extends AbstractKmipStructureTestSuite<InteropOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<InteropOpRequestPayload> type() {
    return InteropOpRequestPayload.class;
  }

  @Override
  protected InteropOpRequestPayload createDefault() {
    return InteropOpRequestPayload
        .builder()
        .interopFunction(InteropFunction.Standard.BEGIN.inst())
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
    assertThat(values.get(0)).isInstanceOf(InteropFunction.class);
  }
}