package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.model.core.structure.KeyValueLocation;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

public class KeyValueLocationBenchmarkSubject extends KmipBenchmarkSubject<KeyValueLocation> {

  public KeyValueLocationBenchmarkSubject() throws Exception {
    KeyValueLocation keyvaluelocation = KeyValueLocation
        .builder()
        .keyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst())
        .keyValueLocationValue(KeyValueLocationValue
            .builder()
            .value("test")
            .build())
        .build();
    initialize(keyvaluelocation, KeyValueLocation.class);
  }

  @Override
  public String name() {
    return "KeyValueLocation";
  }

}