package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;

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