package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.InvocationFieldLength;

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