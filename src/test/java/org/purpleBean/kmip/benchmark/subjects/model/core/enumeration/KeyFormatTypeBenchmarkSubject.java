package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyFormatType> {

  public KeyFormatTypeBenchmarkSubject() throws Exception {
    KeyFormatType keyFormatType = KeyFormatType.Standard.RAW.inst();
    initialize(keyFormatType, KeyFormatType.class);
  }

  @Override
  public String name() {
    return "KeyFormatType";
  }

}
