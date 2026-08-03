package org.purpleBean.kmip.model.core.structure.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SimpleRequestPayload Domain Tests")
class SimpleRequestPayloadTest extends AbstractKmipStructureTestSuite<SimpleRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<SimpleRequestPayload> type() {
    return SimpleRequestPayload.class;
  }

  @Override
  protected SimpleRequestPayload createDefault() {
    return SimpleRequestPayload
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
    assertThat(values).isEmpty();
  }
}