package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import java.util.Set;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.structure.OpaqueObject;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

public class OpaqueObjectBenchmarkSubject extends KmipBenchmarkSubject<OpaqueObject> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public OpaqueObjectBenchmarkSubject() throws Exception {
    OpaqueObject subject = OpaqueObject
        .builder()
        .opaqueDataType(OpaqueDataType
            .register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion))
            .inst())
        .opaqueDataValue(OpaqueDataValue.of(new byte[0]))
        .build();
    initialize(subject, OpaqueObject.class);
  }

  @Override
  public String name() {
    return "OpaqueObject";
  }

}