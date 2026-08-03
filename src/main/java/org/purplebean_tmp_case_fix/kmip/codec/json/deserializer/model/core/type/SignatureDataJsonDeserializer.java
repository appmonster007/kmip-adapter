package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.SignatureData;

public class SignatureDataJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SignatureData, SignatureData.SignatureDataBuilder> {

  public SignatureDataJsonDeserializer() {
    super(SignatureData.kmipTag, SignatureData.encodingType);
  }

  @Override
  protected SignatureData.SignatureDataBuilder createBuilder() {
    return SignatureData.builder();
  }

  @Override
  protected void setValue(SignatureData.SignatureDataBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected SignatureData build(SignatureData.SignatureDataBuilder builder) {
    return builder.build();
  }
}
