package org.purpleBean.kmip.benchmark.subjects.model.v3_0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.type.NistSecurityCategory;

public class NistSecurityCategoryBenchmarkSubject
    extends KmipBenchmarkSubject<NistSecurityCategory> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public NistSecurityCategoryBenchmarkSubject() throws Exception {
    NistSecurityCategory subject = NistSecurityCategory.of(123);
    initialize(subject, NistSecurityCategory.class);
  }

  @Override
  public String name() {
    return "NistSecurityCategory";
  }
}