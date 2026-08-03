package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PrimeExponentQ;

/**
 * JSON deserializer for {@link PrimeExponentQ}.
 */
public class PrimeExponentQJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PrimeExponentQ, PrimeExponentQ.PrimeExponentQBuilder> {

  /**
   * Constructs a new {@link PrimeExponentQJsonDeserializer}.
   */
  public PrimeExponentQJsonDeserializer() {
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
