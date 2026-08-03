package org.purpleBean.kmip.model.v2x1.structure.request.payload;

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

@DisplayName("ImportOpRequestPayload Domain Tests")
class ImportOpRequestPayloadTest extends AbstractKmipStructureTestSuite<ImportOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<ImportOpRequestPayload> type() {
    return ImportOpRequestPayload.class;
  }

  @Override
  protected ImportOpRequestPayload createDefault() {
    return ImportOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
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