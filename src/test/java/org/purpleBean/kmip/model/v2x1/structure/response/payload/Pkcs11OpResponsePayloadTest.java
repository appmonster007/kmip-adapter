package org.purpleBean.kmip.model.v2x1.structure.response.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Pkcs11OpResponsePayload Domain Tests")
class Pkcs11OpResponsePayloadTest extends AbstractKmipStructureTestSuite<Pkcs11OpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<Pkcs11OpResponsePayload> type() {
    return Pkcs11OpResponsePayload.class;
  }

  @Override
  protected Pkcs11OpResponsePayload createDefault() {
    return Pkcs11OpResponsePayload
        .builder()
        .pkcs11ReturnCode(org.purpleBean.kmip.model.v2x1.type.Pkcs11ReturnCode.of(0))
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