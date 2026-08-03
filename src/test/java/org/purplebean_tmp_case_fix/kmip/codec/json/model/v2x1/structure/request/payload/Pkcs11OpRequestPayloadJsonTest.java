package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.structure.request.payload.Pkcs11OpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs11OpRequestPayload Json Serialization Tests")
class Pkcs11OpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<Pkcs11OpRequestPayload> {

  @Override
  public Class<Pkcs11OpRequestPayload> type() {
    return Pkcs11OpRequestPayload.class;
  }

  @Override
  public Pkcs11OpRequestPayload createDefault() {
    return Pkcs11OpRequestPayload
        .builder()
        .pkcs11Function(Pkcs11Function
            .register(0x80000003, "X-Test-Fn",
                java.util.Set.of(org.purplebean.kmip.api.KmipSpec.UnknownVersion))
            .inst())
        .build();
  }

  @Override
  public Pkcs11OpRequestPayload createVariant() {
    return Pkcs11OpRequestPayload
        .builder()
        .pkcs11Function(Pkcs11Function
            .register(0x80000004, "X-Var-Fn",
                java.util.Set.of(org.purplebean.kmip.api.KmipSpec.UnknownVersion))
            .inst())
        .build();
  }
}