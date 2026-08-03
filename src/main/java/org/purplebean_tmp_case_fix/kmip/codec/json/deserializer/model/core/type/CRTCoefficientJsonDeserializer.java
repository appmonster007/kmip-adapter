package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CRTCoefficient;

public class CRTCoefficientJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CRTCoefficient, CRTCoefficient.CRTCoefficientBuilder> {

  public CRTCoefficientJsonDeserializer() {
    super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType);
  }

  @Override
  protected CRTCoefficient.CRTCoefficientBuilder createBuilder() {
    return CRTCoefficient.builder();
  }

  @Override
  protected void setValue(CRTCoefficient.CRTCoefficientBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected CRTCoefficient build(CRTCoefficient.CRTCoefficientBuilder builder) {
    return builder.build();
  }
}
