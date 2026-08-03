package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.DigestedData;

public class DigestedDataJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<DigestedData, DigestedData.DigestedDataBuilder> {

  public DigestedDataJsonDeserializer() {
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