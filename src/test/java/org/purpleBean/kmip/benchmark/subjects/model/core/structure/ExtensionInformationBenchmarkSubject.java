package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ExtensionInformation;
import org.purplebean.kmip.model.core.type.ExtensionName;

public class ExtensionInformationBenchmarkSubject
    extends KmipBenchmarkSubject<ExtensionInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ExtensionInformationBenchmarkSubject() throws Exception {
    ExtensionInformation subject = ExtensionInformation
        .builder()
        .extensionName(ExtensionName.of("test-extension"))
        .build();
    initialize(subject, ExtensionInformation.class);
  }

  @Override
  public String name() {
    return "ExtensionInformation";
  }

}