package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.PublicExponent;

/**
 * XML deserializer for {@link PublicExponent}.
 */
public class PublicExponentXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PublicExponent, PublicExponent.PublicExponentBuilder> {

  /**
   * Constructs a new {@link PublicExponentXmlDeserializer}.
   */
  public PublicExponentXmlDeserializer() {
    super(PublicExponent.kmipTag, PublicExponent.encodingType);
  }

  @Override
  protected PublicExponent.PublicExponentBuilder createBuilder() {
    return PublicExponent.builder();
  }

  @Override
  protected void setValue(PublicExponent.PublicExponentBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected PublicExponent build(PublicExponent.PublicExponentBuilder builder) {
    return builder.build();
  }
}