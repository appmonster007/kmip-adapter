package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.ObjectDefaults;

public class ObjectDefaultsBenchmarkSubject extends KmipBenchmarkSubject<ObjectDefaults> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ObjectDefaultsBenchmarkSubject() throws Exception {
    ObjectDefaults subject = ObjectDefaults
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
        .attributes(Attributes.of(Collections.emptyList()))
        .build();
    initialize(subject, ObjectDefaults.class);
  }

  @Override
  public String name() {
    return "ObjectDefaults";
  }
}
