package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.LocatedItems;

public class LocatedItemsBenchmarkSubject extends KmipBenchmarkSubject<LocatedItems> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public LocatedItemsBenchmarkSubject() throws Exception {
    LocatedItems subject = LocatedItems.of(123);
    initialize(subject, LocatedItems.class);
  }

  @Override
  public String name() {
    return "LocatedItems";
  }
}