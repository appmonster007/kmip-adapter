package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import java.util.Set;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.OpaqueDataType;

/**
 * Benchmark subject for {@link OpaqueDataType}.
 */
public class OpaqueDataTypeBenchmarkSubject extends KmipBenchmarkSubject<OpaqueDataType> {

  /**
   * Constructs a new {@link OpaqueDataTypeBenchmarkSubject}.
   */
  public OpaqueDataTypeBenchmarkSubject() throws Exception {
    OpaqueDataType opaqueDataType = OpaqueDataType
        .register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion))
        .inst();
    initialize(opaqueDataType, OpaqueDataType.class);
  }

  @Override
  public String name() {
    return "OpaqueDataType";
  }

}
