package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

public class SecretDataTypeBenchmarkSubject extends KmipBenchmarkSubject<SecretDataType> {

  public SecretDataTypeBenchmarkSubject() throws Exception {
    SecretDataType secretDataType = SecretDataType.Standard.PASSWORD.inst();
    initialize(secretDataType, SecretDataType.class);
  }

  @Override
  public String name() {
    return "SecretDataType";
  }

}
