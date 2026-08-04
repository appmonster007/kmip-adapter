package org.purplebean.kmip.benchmark.subjects.model.core.type;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SaltLength;

public class SaltLengthBenchmarkSubject extends KmipBenchmarkSubject<SaltLength> {

  public SaltLengthBenchmarkSubject() throws Exception {
    SaltLength saltLength = SaltLength.of(123);
    initialize(saltLength, SaltLength.class);
  }

  @Override
  public String name() {
    return "SaltLength";
  }

}
