package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.RotateOffset;

/**
 * XML deserializer for {@link RotateOffset}.
 */
public class RotateOffsetXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<RotateOffset, RotateOffset.RotateOffsetBuilder> {

  /**
   * Constructs a new {@link RotateOffsetXmlDeserializer}.
   */
  public RotateOffsetXmlDeserializer() {
    super(RotateOffset.kmipTag, RotateOffset.encodingType);
  }

  @Override
  protected RotateOffset.RotateOffsetBuilder createBuilder() {
    return RotateOffset.builder();
  }

  @Override
  protected void setValue(RotateOffset.RotateOffsetBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Long.class));
  }

  @Override
  protected RotateOffset build(RotateOffset.RotateOffsetBuilder builder) {
    return builder.build();
  }
}