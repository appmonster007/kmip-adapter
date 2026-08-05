package org.purplebean.kmip.model.v2x1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RecertifyOpRequestPayload Domain Tests")
class RecertifyOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<RecertifyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<RecertifyOpRequestPayload> type() {
    return RecertifyOpRequestPayload.class;
  }

  @Override
  protected RecertifyOpRequestPayload createDefault() {
    return RecertifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("cert-id")
            .build())
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
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
  }
}