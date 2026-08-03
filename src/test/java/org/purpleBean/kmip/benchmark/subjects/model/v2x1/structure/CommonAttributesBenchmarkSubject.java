package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.CommonAttributes;

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
