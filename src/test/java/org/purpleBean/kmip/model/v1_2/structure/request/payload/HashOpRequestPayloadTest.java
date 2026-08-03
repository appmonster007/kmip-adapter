package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("HashOpRequestPayload Domain Tests")
class HashOpRequestPayloadTest extends AbstractKmipStructureTestSuite<HashOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<HashOpRequestPayload> type() {
    return HashOpRequestPayload.class;
  }

  @Override
  protected HashOpRequestPayload createDefault() {
    return HashOpRequestPayload
        .builder()
        .cryptographicParameters(CryptographicParameters
            .builder()
            .build())
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(CryptographicParameters.class);
    assertThat(values.get(1)).isInstanceOf(DataByteString.class);
  }
}
