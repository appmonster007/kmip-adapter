package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.SecretDataType;

/**
 * Benchmark subject for {@link SecretDataType}.
 */
public class SecretDataTypeBenchmarkSubject extends KmipBenchmarkSubject<SecretDataType> {

  /**
   * Constructs a new {@link SecretDataTypeBenchmarkSubject}.
   */
  public SecretDataTypeBenchmarkSubject() throws Exception {
    SecretDataType secretDataType = SecretDataType.Standard.PASSWORD.inst();
    initialize(secretDataType, SecretDataType.class);
  }

  @Override
  public String name() {
    return "SecretDataType";
  }

}
