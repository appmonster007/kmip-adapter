package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;

/**
 * Benchmark subject for {@link ObjectGroups}.
 */
public class ObjectGroupsBenchmarkSubject extends KmipBenchmarkSubject<ObjectGroups> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ObjectGroupsBenchmarkSubject}.
   */
  public ObjectGroupsBenchmarkSubject() throws Exception {
    ObjectGroups subject = ObjectGroups.of(java.util.List.of());
    initialize(subject, ObjectGroups.class);
  }

  @Override
  public String name() {
    return "ObjectGroups";
  }
}