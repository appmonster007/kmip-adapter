package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyCompressionType> {

  public KeyCompressionTypeBenchmarkSubject() throws Exception {
    KeyCompressionType keyCompressionType =
        KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_UNCOMPRESSED.inst();
    initialize(keyCompressionType, KeyCompressionType.class);
  }

  @Override
  public String name() {
    return "KeyCompressionType";
  }

}
