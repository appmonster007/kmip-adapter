package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

public class InvocationFieldLengthBenchmarkSubject
    extends KmipBenchmarkSubject<InvocationFieldLength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public InvocationFieldLengthBenchmarkSubject() throws Exception {
    InvocationFieldLength invocationFieldLength = InvocationFieldLength.of(128);
    initialize(invocationFieldLength, InvocationFieldLength.class);
  }

  @Override
  public String name() {
    return "InvocationFieldLength";
  }

}