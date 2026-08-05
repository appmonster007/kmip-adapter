package org.purplebean.kmip.model.v2x1.structure.response.payload;

import java.nio.ByteBuffer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Pkcs11Interface;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

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
        .pkcs11Interface(Pkcs11Interface.of("V3.0"))
        .pkcs11Function(Pkcs11Function.of(Pkcs11Function.Standard.INITIALIZE))
        .pkcs11ReturnCode(Pkcs11ReturnCode.of(Pkcs11ReturnCode.Standard.OK))
        .correlationValue(CorrelationValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02})))
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