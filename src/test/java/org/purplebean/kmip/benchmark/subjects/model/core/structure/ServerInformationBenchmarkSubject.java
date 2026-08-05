package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ServerInformation;
import org.purplebean.kmip.model.core.type.NameValue;

/**
 * Benchmark subject for {@link ServerInformation}.
 */
public class ServerInformationBenchmarkSubject extends KmipBenchmarkSubject<ServerInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ServerInformationBenchmarkSubject}.
   */
  public ServerInformationBenchmarkSubject() throws Exception {
    ServerInformation subject = ServerInformation
        .builder()
        .value(NameValue.of("Test Server"))
        .build();
    initialize(subject, ServerInformation.class);
  }

  @Override
  public String name() {
    return "ServerInformation";
  }
}
