package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

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
