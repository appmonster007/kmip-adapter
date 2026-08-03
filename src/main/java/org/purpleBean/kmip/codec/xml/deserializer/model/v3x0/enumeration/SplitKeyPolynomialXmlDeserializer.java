package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3x0.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SplitKeyPolynomial,
        SplitKeyPolynomial.SplitKeyPolynomialBuilder> {

  public SplitKeyPolynomialXmlDeserializer() {
    super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType);
  }

  @Override
  protected SplitKeyPolynomial.SplitKeyPolynomialBuilder createBuilder() {
    return SplitKeyPolynomial.builder();
  }

  @Override
  protected void setValue(SplitKeyPolynomial.SplitKeyPolynomialBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(SplitKeyPolynomial.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected SplitKeyPolynomial build(SplitKeyPolynomial.SplitKeyPolynomialBuilder builder) {
    return builder.build();
  }
}