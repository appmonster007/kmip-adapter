package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ServerInformation;
import org.purpleBean.kmip.model.core.type.NameValue;

public class ServerInformationBenchmarkSubject extends KmipBenchmarkSubject<ServerInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

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
