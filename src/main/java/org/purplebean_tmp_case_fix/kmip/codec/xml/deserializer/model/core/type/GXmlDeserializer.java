package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.G;

public class GXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<G, G.GBuilder> {

  public GXmlDeserializer() {
    super(G.kmipTag, G.encodingType);
  }

  @Override
  protected G.GBuilder createBuilder() {
    return G.builder();
  }

  @Override
  protected void setValue(G.GBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected G build(G.GBuilder builder) {
    return builder.build();
  }
}