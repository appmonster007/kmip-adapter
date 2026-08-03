package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PrimeExponentP;

public class PrimeExponentPJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PrimeExponentP, PrimeExponentP.PrimeExponentPBuilder> {

  public PrimeExponentPJsonDeserializer() {
    super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType);
  }

  @Override
  protected PrimeExponentP.PrimeExponentPBuilder createBuilder() {
    return PrimeExponentP.builder();
  }

  @Override
  protected void setValue(PrimeExponentP.PrimeExponentPBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected PrimeExponentP build(PrimeExponentP.PrimeExponentPBuilder builder) {
    return builder.build();
  }
}
