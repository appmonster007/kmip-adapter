package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.ObjectTypes;

/**
 * Benchmark subject for {@link ObjectTypes}.
 */
public class ObjectTypesBenchmarkSubject extends KmipBenchmarkSubject<ObjectTypes> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ObjectTypesBenchmarkSubject}.
   */
  public ObjectTypesBenchmarkSubject() throws Exception {
    ObjectTypes subject = ObjectTypes.of(java.util.List.of());
    initialize(subject, ObjectTypes.class);
  }

  @Override
  public String name() {
    return "ObjectTypes";
  }
}