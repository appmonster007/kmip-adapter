package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;

/**
 * Benchmark subject for {@link ProtocolVersion}.
 */
public class ProtocolVersionBenchmarkSubject extends KmipBenchmarkSubject<ProtocolVersion> {

  /**
   * Constructs a new {@link ProtocolVersionBenchmarkSubject}.
   */
  public ProtocolVersionBenchmarkSubject() throws Exception {
    ProtocolVersion protocolVersion = ProtocolVersion.of(1, 0);
    initialize(protocolVersion, ProtocolVersion.class);
  }

  @Override
  public String name() {
    return "ProtocolVersion";
  }

}
