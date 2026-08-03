package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.OpaqueDataValue;

public class OpaqueDataValueBenchmarkSubject extends KmipBenchmarkSubject<OpaqueDataValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public OpaqueDataValueBenchmarkSubject() throws Exception {
    OpaqueDataValue opaqueDataValue = OpaqueDataValue.of(new byte[] {0x01, 0x02, 0x03});
    initialize(opaqueDataValue, OpaqueDataValue.class);
  }

  @Override
  public String name() {
    return "OpaqueDataValue";
  }

}