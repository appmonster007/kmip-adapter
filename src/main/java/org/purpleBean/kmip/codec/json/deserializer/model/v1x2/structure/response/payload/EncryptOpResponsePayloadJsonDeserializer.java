package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.EncryptOpResponsePayload;

public class EncryptOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<EncryptOpResponsePayload,
        EncryptOpResponsePayload.EncryptOpResponsePayloadBuilder> {

  public EncryptOpResponsePayloadJsonDeserializer() {
    super(EncryptOpResponsePayload.kmipTag, EncryptOpResponsePayload.encodingType);
  }

  @Override
  protected EncryptOpResponsePayload.EncryptOpResponsePayloadBuilder createBuilder() {
    return EncryptOpResponsePayload.builder();
  }

  @Override
  protected void setValue(EncryptOpResponsePayload.EncryptOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      case KmipTag.Standard.IV_COUNTER_NONCE ->
          builder.ivCounterNonce(ctxt.readValue(p, IVCounterNonce.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected EncryptOpResponsePayload build(
      EncryptOpResponsePayload.EncryptOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
