package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.HashOpResponsePayload;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;

public class HashOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<HashOpResponsePayload,
        HashOpResponsePayload.HashOpResponsePayloadBuilder> {

  public HashOpResponsePayloadJsonDeserializer() {
    super(HashOpResponsePayload.kmipTag, HashOpResponsePayload.encodingType);
  }

  @Override
  protected HashOpResponsePayload.HashOpResponsePayloadBuilder createBuilder() {
    return HashOpResponsePayload.builder();
  }

  @Override
  protected void setValue(HashOpResponsePayload.HashOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected HashOpResponsePayload build(
      HashOpResponsePayload.HashOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
