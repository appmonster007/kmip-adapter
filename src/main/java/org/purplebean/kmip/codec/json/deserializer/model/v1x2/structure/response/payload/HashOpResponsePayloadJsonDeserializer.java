package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.response.payload.HashOpResponsePayload;

/**
 * JSON deserializer for {@link HashOpResponsePayload}.
 */
public class HashOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<HashOpResponsePayload,
        HashOpResponsePayload.HashOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link HashOpResponsePayloadJsonDeserializer}.
   */
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
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected HashOpResponsePayload build(
      HashOpResponsePayload.HashOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
