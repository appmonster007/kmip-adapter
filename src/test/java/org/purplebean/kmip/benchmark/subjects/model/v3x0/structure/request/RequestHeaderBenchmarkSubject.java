package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.request;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.v3x0.structure.request.RequestHeader;

/**
 * Benchmark subject for {@link RequestHeader}.
 */
public class RequestHeaderBenchmarkSubject extends KmipBenchmarkSubject<RequestHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link RequestHeaderBenchmarkSubject}.
   */
  public RequestHeaderBenchmarkSubject() throws Exception {
    RequestHeader subject = RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(3, 0))
        .build();
    initialize(subject, RequestHeader.class);
  }

  @Override
  public String name() {
    return "RequestHeader";
  }
}