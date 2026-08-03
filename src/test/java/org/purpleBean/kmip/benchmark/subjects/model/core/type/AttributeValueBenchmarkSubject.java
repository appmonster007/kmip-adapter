package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeValue;

public class AttributeValueBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AttributeValueBenchmarkSubject() throws Exception {
    AttributeValue subject = AttributeValue.ofTextString("default-string");
    initialize(subject, AttributeValue.class);
  }

  @Override
  public String name() {
    return "AttributeValue";
  }
}