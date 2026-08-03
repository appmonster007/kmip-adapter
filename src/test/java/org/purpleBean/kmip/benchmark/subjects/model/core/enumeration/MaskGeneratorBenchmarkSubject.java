package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;

public class MaskGeneratorBenchmarkSubject extends KmipBenchmarkSubject<MaskGenerator> {

  public MaskGeneratorBenchmarkSubject() throws Exception {
    MaskGenerator maskGenerator = MaskGenerator.Standard.MFG1.inst();
    initialize(maskGenerator, MaskGenerator.class);
  }

  @Override
  public String name() {
    return "MaskGenerator";
  }

}
