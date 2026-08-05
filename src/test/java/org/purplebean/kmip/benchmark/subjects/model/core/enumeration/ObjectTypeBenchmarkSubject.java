package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;

/**
 * Benchmark subject for {@link ObjectType}.
 */
public class ObjectTypeBenchmarkSubject extends KmipBenchmarkSubject<ObjectType> {

  /**
   * Constructs a new {@link ObjectTypeBenchmarkSubject}.
   */
  public ObjectTypeBenchmarkSubject() throws Exception {
    ObjectType objectType = ObjectType.Standard.CERTIFICATE.inst();
    initialize(objectType, ObjectType.class);
  }

  @Override
  public String name() {
    return "ObjectType";
  }

}
