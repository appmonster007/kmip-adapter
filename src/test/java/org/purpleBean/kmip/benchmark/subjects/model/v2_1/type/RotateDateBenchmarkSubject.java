package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.RotateDate;

public class RotateDateBenchmarkSubject extends KmipBenchmarkSubject<RotateDate> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public RotateDateBenchmarkSubject() throws Exception {
    RotateDate subject = RotateDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
    initialize(subject, RotateDate.class);
  }

  @Override
  public String name() {
    return "RotateDate";
  }
}