package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2x1.structure.ObjectDefaults;

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
