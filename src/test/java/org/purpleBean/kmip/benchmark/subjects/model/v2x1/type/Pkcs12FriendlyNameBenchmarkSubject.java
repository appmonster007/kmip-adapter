package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.Pkcs12FriendlyName;

public class Pkcs12FriendlyNameBenchmarkSubject extends KmipBenchmarkSubject<Pkcs12FriendlyName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public Pkcs12FriendlyNameBenchmarkSubject() throws Exception {
    Pkcs12FriendlyName subject = Pkcs12FriendlyName.of("default-string");
    initialize(subject, Pkcs12FriendlyName.class);
  }

  @Override
  public String name() {
    return "Pkcs12FriendlyName";
  }
}