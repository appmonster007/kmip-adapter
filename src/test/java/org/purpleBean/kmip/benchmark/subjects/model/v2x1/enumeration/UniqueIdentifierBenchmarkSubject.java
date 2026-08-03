package org.purpleBean.kmip.benchmark.subjects.model.v2x1.enumeration;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.enumeration.UniqueIdentifier;

public class UniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<UniqueIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  public UniqueIdentifierBenchmarkSubject() throws Exception {
    UniqueIdentifier subject =
        UniqueIdentifier.Standard.values()[0].inst();  // TODO: Create a default instance
    initialize(subject, UniqueIdentifier.class);
  }

  @Override
  public String name() {
    return "UniqueIdentifier";
  }
}