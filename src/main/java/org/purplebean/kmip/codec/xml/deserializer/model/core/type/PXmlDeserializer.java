package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.P;

/**
 * XML deserializer for {@link P}.
 */
public class PXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<P, P.PBuilder> {

  /**
   * Constructs a new {@link PXmlDeserializer}.
   */
  public PXmlDeserializer() {
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