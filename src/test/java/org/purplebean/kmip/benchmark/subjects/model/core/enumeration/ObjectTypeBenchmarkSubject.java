package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeBenchmarkSubject extends KmipBenchmarkSubject<ObjectType> {

  public ObjectTypeBenchmarkSubject() throws Exception {
    ObjectType objectType = ObjectType.Standard.CERTIFICATE.inst();
    initialize(objectType, ObjectType.class);
  }

  @Override
  public String name() {
    return "ObjectType";
  }

}
