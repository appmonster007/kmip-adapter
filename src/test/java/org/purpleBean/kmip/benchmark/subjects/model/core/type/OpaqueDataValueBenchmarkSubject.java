package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

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