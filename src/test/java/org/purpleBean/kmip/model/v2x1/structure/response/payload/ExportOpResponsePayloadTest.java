package org.purpleBean.kmip.model.v2x1.structure.response.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ExportOpResponsePayload Domain Tests")
class ExportOpResponsePayloadTest extends AbstractKmipStructureTestSuite<ExportOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<ExportOpResponsePayload> type() {
    return ExportOpResponsePayload.class;
  }

  @Override
  protected ExportOpResponsePayload createDefault() {
    return ExportOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
        .uniqueIdentifier(org.purpleBean.kmip.model.core.type.UniqueIdentifier.of("cert-1"))
        .object(Certificate.of(CertificateType.Standard.X_509.inst(),
            CertificateValue.of(new byte[] {0x01})))
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