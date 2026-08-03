package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;

public class AlternativeNameValueBenchmarkSubject
    extends KmipBenchmarkSubject<AlternativeNameValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public AlternativeNameValueBenchmarkSubject() throws Exception {
    AlternativeNameValue alternativeNameValue = AlternativeNameValue.of("some value");
    initialize(alternativeNameValue, AlternativeNameValue.class);
  }

  @Override
  public String name() {
    return "AlternativeNameValue";
  }

}