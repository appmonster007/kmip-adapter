package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.AddAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AddAttributeOpRequestPayload Ttlv Serialization Tests")
class AddAttributeOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<AddAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purpleBean.kmip.api.KmipSpec.V2_1;
  }

  @Override
  public Class<AddAttributeOpRequestPayload> type() {
    return AddAttributeOpRequestPayload.class;
  }

  @Override
  public AddAttributeOpRequestPayload createDefault() {
    return AddAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
  }

  @Override
  public AddAttributeOpRequestPayload createVariant() {
    return AddAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid-2"))
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
            .build())
        .build();
  }
}