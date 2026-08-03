package org.purplebean.kmip.benchmark.subjects.model.core.type;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CryptographicLength;

public class CryptographicLengthBenchmarkSubject extends KmipBenchmarkSubject<CryptographicLength> {

  public CryptographicLengthBenchmarkSubject() throws Exception {
    CryptographicLength cryptographicLength = CryptographicLength.of(256);
    initialize(cryptographicLength, CryptographicLength.class);
  }

  @Override
  public String name() {
    return "CryptographicLength";
  }

}
