package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.Offset;

/**
 * XML deserializer for {@link Offset}.
 */
public class OffsetXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Offset, Offset.OffsetBuilder> {

  /**
   * Constructs a new {@link OffsetXmlDeserializer}.
   */
  public OffsetXmlDeserializer() {
    super(Offset.kmipTag, Offset.encodingType);
  }

  @Override
  protected Offset.OffsetBuilder createBuilder() {
    return Offset.builder();
  }

  @Override
  protected void setValue(Offset.OffsetBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected Offset build(Offset.OffsetBuilder builder) {
    return builder.build();
  }
}