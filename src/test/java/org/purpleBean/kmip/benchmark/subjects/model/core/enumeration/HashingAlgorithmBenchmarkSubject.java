package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

public class HashingAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<HashingAlgorithm> {

  public HashingAlgorithmBenchmarkSubject() throws Exception {
    HashingAlgorithm hashingAlgorithm = HashingAlgorithm.Standard.MD2.inst();
    initialize(hashingAlgorithm, HashingAlgorithm.class);
  }

  @Override
  public String name() {
    return "HashingAlgorithm";
  }

}
