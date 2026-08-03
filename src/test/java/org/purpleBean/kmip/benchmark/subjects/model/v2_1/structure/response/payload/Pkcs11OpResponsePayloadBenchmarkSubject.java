package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.Pkcs11OpResponsePayload;

public class Pkcs11OpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<Pkcs11OpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public Pkcs11OpResponsePayloadBenchmarkSubject() throws Exception {
    Pkcs11OpResponsePayload subject = Pkcs11OpResponsePayload
        .builder()
        .pkcs11ReturnCode(org.purpleBean.kmip.model.v2_1.type.Pkcs11ReturnCode.of(0))
        .build();
    initialize(subject, Pkcs11OpResponsePayload.class);
  }

  @Override
  public String name() {
    return "Pkcs11OpResponsePayload";
  }
}