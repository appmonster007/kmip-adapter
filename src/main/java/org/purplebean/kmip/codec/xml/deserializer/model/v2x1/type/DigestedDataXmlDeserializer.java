package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.DigestedData;

/**
 * XML deserializer for {@link DigestedData}.
 */
public class DigestedDataXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<DigestedData, DigestedData.DigestedDataBuilder> {

  /**
   * Constructs a new {@link DigestedDataXmlDeserializer}.
   */
  public DigestedDataXmlDeserializer() {
    super(DigestedData.kmipTag, DigestedData.encodingType);
  }

  @Override
  protected DigestedData.DigestedDataBuilder createBuilder() {
    return DigestedData.builder();
  }

  @Override
  protected void setValue(DigestedData.DigestedDataBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected DigestedData build(DigestedData.DigestedDataBuilder builder) {
    return builder.build();
  }
}