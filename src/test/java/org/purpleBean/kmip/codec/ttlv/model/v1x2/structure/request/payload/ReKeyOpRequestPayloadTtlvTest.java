package org.purpleBean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.ReKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReKeyOpRequestPayload Ttlv Serialization Tests")
class ReKeyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ReKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ReKeyOpRequestPayload> type() {
    return ReKeyOpRequestPayload.class;
  }

  @Override
  public ReKeyOpRequestPayload createDefault() {
    return ReKeyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .offset(Offset
            .builder()
            .value(100)
            .build())
        .templateAttribute(TemplateAttribute
            .builder()
            .build())
        .build();
  }

  @Override
  public ReKeyOpRequestPayload createVariant() {
    return ReKeyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid2")
            .build())
        .offset(Offset
            .builder()
            .value(200)
            .build())
        .build();
  }
}