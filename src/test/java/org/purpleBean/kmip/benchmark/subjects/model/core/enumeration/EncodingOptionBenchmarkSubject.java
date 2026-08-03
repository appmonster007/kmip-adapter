package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionBenchmarkSubject extends KmipBenchmarkSubject<EncodingOption> {

  public EncodingOptionBenchmarkSubject() throws Exception {
    EncodingOption encodingOption = EncodingOption.Standard.NO_ENCODING.inst();
    initialize(encodingOption, EncodingOption.class);
  }

  @Override
  public String name() {
    return "EncodingOption";
  }

}
