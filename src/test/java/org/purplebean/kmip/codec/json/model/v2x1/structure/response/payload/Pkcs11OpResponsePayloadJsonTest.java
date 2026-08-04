package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.Pkcs11OpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Pkcs11ReturnCode;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs11OpResponsePayload Json Serialization Tests")
class Pkcs11OpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<Pkcs11OpResponsePayload> {

  @Override
  public Class<Pkcs11OpResponsePayload> type() {
    return Pkcs11OpResponsePayload.class;
  }

  @Override
  public Pkcs11OpResponsePayload createDefault() {
    return Pkcs11OpResponsePayload
        .builder()
        .pkcs11ReturnCode(Pkcs11ReturnCode.of(0))
        .correlationValue(CorrelationValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02})))
        .build();
  }

  @Override
  public Pkcs11OpResponsePayload createVariant() {
    return Pkcs11OpResponsePayload
        .builder()
        .pkcs11ReturnCode(Pkcs11ReturnCode.of(1))
        .correlationValue(CorrelationValue.of(ByteBuffer.wrap(new byte[] {0x03, 0x04})))
        .build();
  }
}