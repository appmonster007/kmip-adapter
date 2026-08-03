package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.KeyValueLocationValue;

public class KeyValueLocationValueBenchmarkSubject
    extends KmipBenchmarkSubject<KeyValueLocationValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public KeyValueLocationValueBenchmarkSubject() throws Exception {
    KeyValueLocationValue keyValueLocationValue = KeyValueLocationValue
        .builder()
        .value("test")
        .build();
    initialize(keyValueLocationValue, KeyValueLocationValue.class);
  }

  @Override
  public String name() {
    return "KeyValueLocationValue";
  }

}