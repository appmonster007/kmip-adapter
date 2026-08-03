package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyWrapType;

public class KeyWrapTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyWrapType> {

  public KeyWrapTypeBenchmarkSubject() throws Exception {
    KeyWrapType keyWrapType = KeyWrapType.Standard.NOT_WRAPPED.inst();
    initialize(keyWrapType, KeyWrapType.class);
  }

  @Override
  public String name() {
    return "KeyWrapType";
  }

}
