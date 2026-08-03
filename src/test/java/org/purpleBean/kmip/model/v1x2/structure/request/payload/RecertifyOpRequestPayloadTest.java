package org.purpleBean.kmip.model.v1x2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RecertifyOpRequestPayload Domain Tests")
class RecertifyOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<RecertifyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<RecertifyOpRequestPayload> type() {
    return RecertifyOpRequestPayload.class;
  }

  @Override
  protected RecertifyOpRequestPayload createDefault() {
    return RecertifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .certificateRequestType(CertificateRequestType.Standard.PKCS_10.inst())
        .certificateRequest(CertificateRequest.of(new byte[] {0x01, 0x02, 0x03}))
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
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 0; // All fields are optional
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(5);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(CertificateRequestType.class);
    assertThat(values.get(2)).isInstanceOf(CertificateRequest.class);
    assertThat(values.get(3)).isInstanceOf(Offset.class);
    assertThat(values.get(4)).isInstanceOf(TemplateAttribute.class);
  }
}