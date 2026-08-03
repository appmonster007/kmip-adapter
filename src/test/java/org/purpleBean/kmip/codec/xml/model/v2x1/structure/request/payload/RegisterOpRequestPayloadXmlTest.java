package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.RegisterOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RegisterOpRequestPayload Xml Serialization Tests")
class RegisterOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RegisterOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purpleBean.kmip.api.KmipSpec.V2_1;
  }

  @Override
  public Class<RegisterOpRequestPayload> type() {
    return RegisterOpRequestPayload.class;
  }

  @Override
  public RegisterOpRequestPayload createDefault() {
    return RegisterOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .attributes(Attributes
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .object(SymmetricKey
            .builder()
            .keyBlock(KeyBlock
                .builder()
                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                .build())
            .build())
        .build();
  }

  @Override
  public RegisterOpRequestPayload createVariant() {
    return RegisterOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .attributes(Attributes
            .builder()
            .attribute(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
            .build())
        .object(SymmetricKey
            .builder()
            .keyBlock(KeyBlock
                .builder()
                .keyFormatType(KeyFormatType.Standard.RAW.inst())
                .build())
            .build())
        .build();
  }
}