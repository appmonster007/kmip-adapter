package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.NonceId;

/**
 * XML deserializer for {@link NonceId}.
 */
public class NonceIdXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<NonceId, NonceId.NonceIdBuilder> {

  /**
   * Constructs a new {@link NonceIdXmlDeserializer}.
   */
  public NonceIdXmlDeserializer() {
    super(NonceId.kmipTag, NonceId.encodingType);
  }

  @Override
  protected NonceId.NonceIdBuilder createBuilder() {
    return NonceId.builder();
  }

  @Override
  protected void setValue(NonceId.NonceIdBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected NonceId build(NonceId.NonceIdBuilder builder) {
    return builder.build();
  }
}