package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.PublicKeyAttributes;

public class PublicKeyAttributesBenchmarkSubject extends KmipBenchmarkSubject<PublicKeyAttributes> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public PublicKeyAttributesBenchmarkSubject() throws Exception {
    PublicKeyAttributes subject = PublicKeyAttributes.of(Collections.emptyList());
    initialize(subject, PublicKeyAttributes.class);
  }

  @Override
  public String name() {
    return "PublicKeyAttributes";
  }
}
