package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.PrimeExponentQ;

public class PrimeExponentQXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PrimeExponentQ, PrimeExponentQ.PrimeExponentQBuilder> {

  public PrimeExponentQXmlDeserializer() {
    super(PrimeExponentQ.kmipTag, PrimeExponentQ.encodingType);
  }

  @Override
  protected PrimeExponentQ.PrimeExponentQBuilder createBuilder() {
    return PrimeExponentQ.builder();
  }

  @Override
  protected void setValue(PrimeExponentQ.PrimeExponentQBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected PrimeExponentQ build(PrimeExponentQ.PrimeExponentQBuilder builder) {
    return builder.build();
  }
}