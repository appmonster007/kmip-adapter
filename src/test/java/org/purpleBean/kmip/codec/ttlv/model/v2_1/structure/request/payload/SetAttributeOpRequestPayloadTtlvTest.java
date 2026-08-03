package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetAttributeOpRequestPayload Ttlv Serialization Tests")
class SetAttributeOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SetAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<SetAttributeOpRequestPayload> type() {
    return SetAttributeOpRequestPayload.class;
  }

  @Override
  public SetAttributeOpRequestPayload createDefault() {
    return SetAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("set-attr-uid-1")
            .build())
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
  }

  @Override
  public SetAttributeOpRequestPayload createVariant() {
    return SetAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("set-attr-uid-2")
            .build())
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.RSA.inst())
            .build())
        .build();
  }
}
