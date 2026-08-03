package org.purplebean.kmip.codec.json.deserializer.model.v3x0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.enumeration.SplitKeyPolynomial;

/**
 * JSON deserializer for {@link SplitKeyPolynomial}.
 */
public class SplitKeyPolynomialJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SplitKeyPolynomial,
        SplitKeyPolynomial.SplitKeyPolynomialBuilder> {

  /**
   * Constructs a new {@link SplitKeyPolynomialJsonDeserializer}.
   */
  public SplitKeyPolynomialJsonDeserializer() {
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
