package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.TagLength;

/**
 * XML deserializer for {@link TagLength}.
 */
public class TagLengthXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<TagLength, TagLength.TagLengthBuilder> {

  /**
   * Constructs a new {@link TagLengthXmlDeserializer}.
   */
  public TagLengthXmlDeserializer() {
    super(TagLength.kmipTag, TagLength.encodingType);
  }

  @Override
  protected TagLength.TagLengthBuilder createBuilder() {
    return TagLength.builder();
  }

  @Override
  protected void setValue(TagLength.TagLengthBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected TagLength build(TagLength.TagLengthBuilder builder) {
    return builder.build();
  }
}