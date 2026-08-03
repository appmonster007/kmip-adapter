package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyVersionBenchmarkSubject extends KmipBenchmarkSubject<PgpKeyVersion> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public PgpKeyVersionBenchmarkSubject() throws Exception {
    PgpKeyVersion subject = PgpKeyVersion.of(123);
    initialize(subject, PgpKeyVersion.class);
  }

  @Override
  public String name() {
    return "PgpKeyVersion";
  }

}