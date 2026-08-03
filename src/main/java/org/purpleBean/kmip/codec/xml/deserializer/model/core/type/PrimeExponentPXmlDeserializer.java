package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.PrimeExponentP;

public class PrimeExponentPXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PrimeExponentP, PrimeExponentP.PrimeExponentPBuilder> {

  public PrimeExponentPXmlDeserializer() {
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