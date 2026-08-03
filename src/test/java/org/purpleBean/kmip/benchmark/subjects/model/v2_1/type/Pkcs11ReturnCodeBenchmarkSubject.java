package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11ReturnCode;

public class Pkcs11ReturnCodeBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11ReturnCode> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public Pkcs11ReturnCodeBenchmarkSubject() throws Exception {
    Pkcs11ReturnCode subject = Pkcs11ReturnCode.of(1);
    initialize(subject, Pkcs11ReturnCode.class);
  }

  @Override
  public String name() {
    return "Pkcs11ReturnCode";
  }
}