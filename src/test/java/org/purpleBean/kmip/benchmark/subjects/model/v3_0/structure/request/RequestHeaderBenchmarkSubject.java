package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.v3_0.structure.request.RequestHeader;

public class RequestHeaderBenchmarkSubject extends KmipBenchmarkSubject<RequestHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

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