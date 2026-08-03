package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceBenchmarkSubject
    extends KmipBenchmarkSubject<ApplicationNamespace> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ApplicationNamespaceBenchmarkSubject() throws Exception {
    ApplicationNamespace applicationNamespace = ApplicationNamespace
        .builder()
        .value("test-namespace")
        .build();
    initialize(applicationNamespace, ApplicationNamespace.class);
  }

  @Override
  public String name() {
    return "ApplicationNamespace";
  }

}