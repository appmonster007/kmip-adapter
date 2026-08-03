package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateValue;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ImportOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ImportOpRequestPayload Ttlv Serialization Tests")
class ImportOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ImportOpRequestPayload> {

  @Override
  public Class<ImportOpRequestPayload> type() {
    return ImportOpRequestPayload.class;
  }

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }


  @Override
  public ImportOpRequestPayload createDefault() {
    return ImportOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
        .object(Certificate.of(CertificateType.Standard.X_509.inst(),
            CertificateValue.of(new byte[] {0x01})))
        .build();
  }

  @Override
  public ImportOpRequestPayload createVariant() {
    return ImportOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
        .object(Certificate.of(CertificateType.Standard.X_509.inst(),
            CertificateValue.of(new byte[] {0x02})))
        .build();
  }
}