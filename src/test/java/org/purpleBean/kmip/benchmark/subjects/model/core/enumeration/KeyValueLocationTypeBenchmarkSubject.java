package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeBenchmarkSubject
    extends KmipBenchmarkSubject<KeyValueLocationType> {

  public KeyValueLocationTypeBenchmarkSubject() throws Exception {
    KeyValueLocationType keyValueLocationType =
        KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    initialize(keyValueLocationType, KeyValueLocationType.class);
  }

  @Override
  public String name() {
    return "KeyValueLocationType";
  }

}
