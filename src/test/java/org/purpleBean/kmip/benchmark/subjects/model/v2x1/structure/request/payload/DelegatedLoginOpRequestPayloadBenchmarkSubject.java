package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.DelegatedLoginOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.RequestCount;

public class DelegatedLoginOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DelegatedLoginOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public DelegatedLoginOpRequestPayloadBenchmarkSubject() throws Exception {
    DelegatedLoginOpRequestPayload subject = DelegatedLoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(3600))
        .requestCount(RequestCount.of(10))
        .build();
    initialize(subject, DelegatedLoginOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DelegatedLoginOpRequestPayload";
  }
}