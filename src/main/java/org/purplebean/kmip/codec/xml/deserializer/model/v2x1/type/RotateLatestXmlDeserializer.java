package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.RotateLatest;

/**
 * XML deserializer for {@link RotateLatest}.
 */
public class RotateLatestXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<RotateLatest, RotateLatest.RotateLatestBuilder> {

  /**
   * Constructs a new {@link RotateLatestXmlDeserializer}.
   */
  public RotateLatestXmlDeserializer() {
    super(RotateLatest.kmipTag, RotateLatest.encodingType);
  }

  @Override
  protected RotateLatest.RotateLatestBuilder createBuilder() {
    return RotateLatest.builder();
  }

  @Override
  protected void setValue(RotateLatest.RotateLatestBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected RotateLatest build(RotateLatest.RotateLatestBuilder builder) {
    return builder.build();
  }
}