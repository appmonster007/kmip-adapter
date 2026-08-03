package org.purplebean.kmip.benchmark.subjects.model.v3x0.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.enumeration.ObjectClass;

public class ObjectClassBenchmarkSubject extends KmipBenchmarkSubject<ObjectClass> {

  public ObjectClassBenchmarkSubject() throws Exception {
    ObjectClass objectClass = ObjectClass.Standard.USER.inst();
    initialize(objectClass, ObjectClass.class);
  }

  @Override
  public String name() {
    return "ObjectClass";
  }

}
