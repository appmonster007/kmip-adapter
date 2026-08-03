package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.PutFunction;

public class PutFunctionBenchmarkSubject extends KmipBenchmarkSubject<PutFunction> {

  public PutFunctionBenchmarkSubject() throws Exception {
    PutFunction putFunction = PutFunction.Standard.NEW.inst();
    initialize(putFunction, PutFunction.class);
  }

  @Override
  public String name() {
    return "PutFunction";
  }

}
