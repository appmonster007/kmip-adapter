package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RecoverOpResponsePayload;

/**
 * JSON deserializer for {@link RecoverOpResponsePayload}.
 */
public class RecoverOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RecoverOpResponsePayload,
        RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link RecoverOpResponsePayloadJsonDeserializer}.
   */
  public RecoverOpResponsePayloadJsonDeserializer() {
    super(RecoverOpResponsePayload.kmipTag, RecoverOpResponsePayload.encodingType);
  }

  @Override
  protected RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder createBuilder() {
    return RecoverOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder builder,
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
  protected RecoverOpResponsePayload build(
      RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
