package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DestroyDate;

public class DestroyDateBenchmarkSubject extends KmipBenchmarkSubject<DestroyDate> {

  public DestroyDateBenchmarkSubject() throws Exception {
    var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    DestroyDate destroyDate = DestroyDate
        .builder()
        .value(fixed)
        .build();
    initialize(destroyDate, DestroyDate.class);
  }

  @Override
  public String name() {
    return "DestroyDate";
  }

}
