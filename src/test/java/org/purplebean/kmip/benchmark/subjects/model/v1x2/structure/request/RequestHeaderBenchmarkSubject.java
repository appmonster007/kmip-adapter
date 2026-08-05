package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.v1x2.structure.request.RequestHeader;

/**
 * Benchmark subject for {@link RequestHeader}.
 */
public class RequestHeaderBenchmarkSubject extends KmipBenchmarkSubject<RequestHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link RequestHeaderBenchmarkSubject}.
   */
  public RequestHeaderBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
    RequestHeader subject = RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion
            .builder()
            .protocolVersionMajor(ProtocolVersionMajor.of(1))
            .protocolVersionMinor(ProtocolVersionMinor.of(2))
            .build())
        .batchCount(BatchCount.of(1))
        .build();
    initialize(subject, RequestHeader.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "RequestHeader";
  }

}