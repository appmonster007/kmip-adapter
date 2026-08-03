package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PublicExponent;

/**
 * JSON deserializer for {@link PublicExponent}.
 */
public class PublicExponentJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PublicExponent, PublicExponent.PublicExponentBuilder> {

  /**
   * Constructs a new {@link PublicExponentJsonDeserializer}.
   */
  public PublicExponentJsonDeserializer() {
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
