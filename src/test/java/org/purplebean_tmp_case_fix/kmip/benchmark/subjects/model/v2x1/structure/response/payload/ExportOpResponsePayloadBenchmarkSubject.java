package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.CertificateValue;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ExportOpResponsePayload;

public class ExportOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ExportOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  public ExportOpResponsePayloadBenchmarkSubject() throws Exception {
    ExportOpResponsePayload subject = ExportOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
        .uniqueIdentifier(org.purplebean.kmip.model.core.type.UniqueIdentifier.of("cert-1"))
        .object(Certificate.of(CertificateType.Standard.X_509.inst(),
            CertificateValue.of(new byte[] {0x01})))
        .build();
    initialize(subject, ExportOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ExportOpResponsePayload";
  }
}