package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purplebean.kmip.model.core.type.ApplicationData;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;

/**
 * Benchmark subject for {@link ApplicationSpecificInformation}.
 */
public class ApplicationSpecificInformationBenchmarkSubject
    extends KmipBenchmarkSubject<ApplicationSpecificInformation> {

  /**
   * Constructs a new {@link ApplicationSpecificInformationBenchmarkSubject}.
   */
  public ApplicationSpecificInformationBenchmarkSubject() throws Exception {
    ApplicationSpecificInformation applicationspecificinformation = ApplicationSpecificInformation
        .builder()
        .applicationNamespace(ApplicationNamespace.of("namespace"))
        .applicationData(ApplicationData.of("data"))
        .build();
    initialize(applicationspecificinformation, ApplicationSpecificInformation.class);
  }

  @Override
  public String name() {
    return "ApplicationSpecificInformation";
  }

}