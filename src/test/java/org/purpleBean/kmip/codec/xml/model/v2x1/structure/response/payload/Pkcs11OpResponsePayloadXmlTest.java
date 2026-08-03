package org.purpleBean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.Pkcs11OpResponsePayload;
import org.purpleBean.kmip.model.v2x1.type.Pkcs11ReturnCode;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11OpResponsePayload Xml Serialization Tests")
class Pkcs11OpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<Pkcs11OpResponsePayload> {

  @Override
  public Class<Pkcs11OpResponsePayload> type() {
    return Pkcs11OpResponsePayload.class;
  }

  @Override
  public Pkcs11OpResponsePayload createDefault() {
    return Pkcs11OpResponsePayload
        .builder()
        .pkcs11ReturnCode(Pkcs11ReturnCode.of(0))
        .build();
  }

  @Override
  public Pkcs11OpResponsePayload createVariant() {
    return Pkcs11OpResponsePayload
        .builder()
        .pkcs11ReturnCode(Pkcs11ReturnCode.of(1))
        .build();
  }
}