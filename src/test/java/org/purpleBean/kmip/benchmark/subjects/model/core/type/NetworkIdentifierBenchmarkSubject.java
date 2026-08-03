package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.NetworkIdentifier;

public class NetworkIdentifierBenchmarkSubject extends KmipBenchmarkSubject<NetworkIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public NetworkIdentifierBenchmarkSubject() throws Exception {
    NetworkIdentifier networkIdentifier = NetworkIdentifier
        .builder()
        .value("test-network-id")
        .build();
    initialize(networkIdentifier, NetworkIdentifier.class);
  }

  @Override
  public String name() {
    return "NetworkIdentifier";
  }

}