package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.v2x1.structure.request.RequestHeader;
import org.purplebean.kmip.model.v2x1.structure.request.RequestMessage;

/**
 * Benchmark subject for {@link RequestMessage}.
 */
public class RequestMessageBenchmarkSubject extends KmipBenchmarkSubject<RequestMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link RequestMessageBenchmarkSubject}.
   */
  public RequestMessageBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
    RequestMessage subject = RequestMessage
        .builder()
        .requestHeader(RequestHeader
            .builder()
            .protocolVersion(ProtocolVersion.of(2, 1))
            .batchCount(BatchCount.of(0))
            .build())
        .build();
    initialize(subject, RequestMessage.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "RequestMessage";
  }
}
