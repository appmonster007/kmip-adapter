package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.NetworkIdentifier;

/**
 * Benchmark subject for {@link NetworkIdentifier}.
 */
public class NetworkIdentifierBenchmarkSubject extends KmipBenchmarkSubject<NetworkIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link NetworkIdentifierBenchmarkSubject}.
   */
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