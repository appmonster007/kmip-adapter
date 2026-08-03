package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.P;

public class PJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<P, P.PBuilder> {

  public PJsonDeserializer() {
    super(P.kmipTag, P.encodingType);
  }

  @Override
  protected P.PBuilder createBuilder() {
    return P.builder();
  }

  @Override
  protected void setValue(P.PBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected P build(P.PBuilder builder) {
    return builder.build();
  }
}
