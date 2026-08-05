package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v3x0.structure.response.ResponseHeader;

/**
 * Benchmark subject for {@link ResponseHeader}.
 */
public class ResponseHeaderBenchmarkSubject extends KmipBenchmarkSubject<ResponseHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link ResponseHeaderBenchmarkSubject}.
   */
  public ResponseHeaderBenchmarkSubject() throws Exception {
    ResponseHeader subject = ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(3, 0))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
        .build();
    initialize(subject, ResponseHeader.class);
  }

  @Override
  public String name() {
    return "ResponseHeader";
  }
}