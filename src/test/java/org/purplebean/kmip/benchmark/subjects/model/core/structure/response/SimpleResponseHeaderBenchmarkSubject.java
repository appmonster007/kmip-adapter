package org.purplebean.kmip.benchmark.subjects.model.core.structure.response;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseHeader;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;

/**
 * Benchmark subject for {@link SimpleResponseHeader}.
 */
public class SimpleResponseHeaderBenchmarkSubject
    extends KmipBenchmarkSubject<SimpleResponseHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link SimpleResponseHeaderBenchmarkSubject}.
   */
  public SimpleResponseHeaderBenchmarkSubject() throws Exception {
    SimpleResponseHeader subject = SimpleResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .build();
    initialize(subject, SimpleResponseHeader.class);
  }

  @Override
  public String name() {
    return "SimpleResponseHeader";
  }
}
