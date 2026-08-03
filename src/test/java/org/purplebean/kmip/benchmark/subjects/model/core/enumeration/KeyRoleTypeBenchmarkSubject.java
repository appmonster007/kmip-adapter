package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyRoleType;

public class KeyRoleTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyRoleType> {

  public KeyRoleTypeBenchmarkSubject() throws Exception {
    KeyRoleType keyRoleType = KeyRoleType.Standard.BDK.inst();
    initialize(keyRoleType, KeyRoleType.class);
  }

  @Override
  public String name() {
    return "KeyRoleType";
  }

}
