package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.Pkcs11Function;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.Pkcs11OpRequestPayload;

public class Pkcs11OpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<Pkcs11OpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public Pkcs11OpRequestPayloadBenchmarkSubject() throws Exception {
    Pkcs11OpRequestPayload subject = Pkcs11OpRequestPayload
        .builder()
        .pkcs11Function(Pkcs11Function
            .register(0x80000007, "X-Bench", java.util.Set.of(KmipSpec.UnknownVersion))
            .inst())
        .build();
    initialize(subject, Pkcs11OpRequestPayload.class);
  }

  @Override
  public String name() {
    return "Pkcs11OpRequestPayload";
  }
}