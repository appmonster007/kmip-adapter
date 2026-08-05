package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ObjectGroup;

/**
 * Benchmark subject for {@link ObjectGroup}.
 */
public class ObjectGroupBenchmarkSubject extends KmipBenchmarkSubject<ObjectGroup> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ObjectGroupBenchmarkSubject}.
   */
  public ObjectGroupBenchmarkSubject() throws Exception {
    ObjectGroup objectGroup = ObjectGroup
        .builder()
        .value("test")
        .build();
    initialize(objectGroup, ObjectGroup.class);
  }

  @Override
  public String name() {
    return "ObjectGroup";
  }

}
