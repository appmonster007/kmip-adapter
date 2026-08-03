package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;

public class AdjustmentTypeBenchmarkSubject extends KmipBenchmarkSubject<AdjustmentType> {

  public AdjustmentTypeBenchmarkSubject() throws Exception {
    AdjustmentType adjustmentType = AdjustmentType.Standard.INCREMENT.inst();
    initialize(adjustmentType, AdjustmentType.class);
  }

  @Override
  public String name() {
    return "AdjustmentType";
  }

}
