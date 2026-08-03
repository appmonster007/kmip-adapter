package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.MaskGenerator;

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
