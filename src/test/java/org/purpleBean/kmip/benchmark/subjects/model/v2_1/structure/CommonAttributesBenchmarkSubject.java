package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.CommonAttributes;

public class CommonAttributesBenchmarkSubject extends KmipBenchmarkSubject<CommonAttributes> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public CommonAttributesBenchmarkSubject() throws Exception {
    CommonAttributes subject = CommonAttributes.of(Collections.emptyList());
    initialize(subject, CommonAttributes.class);
  }

  @Override
  public String name() {
    return "CommonAttributes";
  }
}
