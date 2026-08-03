package org.purpleBean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Pkcs11OpRequestPayload Domain Tests")
class Pkcs11OpRequestPayloadTest extends AbstractKmipStructureTestSuite<Pkcs11OpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<Pkcs11OpRequestPayload> type() {
    return Pkcs11OpRequestPayload.class;
  }

  @Override
  protected Pkcs11OpRequestPayload createDefault() {
    return Pkcs11OpRequestPayload
        .builder()
        .pkcs11Function(Pkcs11Function
            .register(0x80000005, "X-Domain-Test", Set.of(KmipSpec.UnknownVersion))
            .inst())
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