package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateValue;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.ImportOpRequestPayload;

public class ImportOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ImportOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  public ImportOpRequestPayloadBenchmarkSubject() throws Exception {
    ImportOpRequestPayload subject = ImportOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
        .object(Certificate.of(CertificateType.Standard.X_509.inst(),
            CertificateValue.of(new byte[] {0x01})))
        .build();
    initialize(subject, ImportOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ImportOpRequestPayload";
  }
}