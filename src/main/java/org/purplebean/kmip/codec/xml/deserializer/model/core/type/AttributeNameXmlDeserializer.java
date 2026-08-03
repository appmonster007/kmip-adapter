package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.AttributeName;

/**
 * XML deserializer for {@link AttributeName}.
 */
public class AttributeNameXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<AttributeName, AttributeName.AttributeNameBuilder> {

  /**
   * Constructs a new {@link AttributeNameXmlDeserializer}.
   */
  public AttributeNameXmlDeserializer() {
    super(AttributeName.kmipTag, AttributeName.encodingType);
  }

  @Override
  protected AttributeName.AttributeNameBuilder createBuilder() {
    return AttributeName.builder();
  }

  @Override
  protected void setValue(AttributeName.AttributeNameBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected AttributeName build(AttributeName.AttributeNameBuilder builder) {
    return builder.build();
  }
}