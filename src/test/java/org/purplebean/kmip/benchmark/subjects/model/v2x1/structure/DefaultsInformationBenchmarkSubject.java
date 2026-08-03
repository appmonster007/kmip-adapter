package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purplebean.kmip.model.v2x1.structure.ObjectDefaults;

public class DefaultsInformationBenchmarkSubject extends KmipBenchmarkSubject<DefaultsInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public DefaultsInformationBenchmarkSubject() throws Exception {
    DefaultsInformation subject = DefaultsInformation.of(List.of(
        ObjectDefaults
            .builder()
            .objectType(ObjectType.Standard.CERTIFICATE.inst())
            .attributes(Attributes.of(Collections.emptyList()))
            .build()
    ));
    initialize(subject, DefaultsInformation.class);
  }

  @Override
  public String name() {
    return "DefaultsInformation";
  }
}
