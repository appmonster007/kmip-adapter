package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DestroyOpResponsePayload;

/**
 * JSON deserializer for {@link DestroyOpResponsePayload}.
 */
public class DestroyOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DestroyOpResponsePayload,
        DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link DestroyOpResponsePayloadJsonDeserializer}.
   */
  public DestroyOpResponsePayloadJsonDeserializer() {
    super(DestroyOpResponsePayload.kmipTag, DestroyOpResponsePayload.encodingType);
  }

  @Override
  protected DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder createBuilder() {
    return DestroyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DestroyOpResponsePayload build(
      DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
