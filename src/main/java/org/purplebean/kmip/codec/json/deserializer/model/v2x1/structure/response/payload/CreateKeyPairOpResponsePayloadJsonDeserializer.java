package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.CreateKeyPairOpResponsePayload;

/**
 * JSON deserializer for {@link CreateKeyPairOpResponsePayload}.
 */
public class CreateKeyPairOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateKeyPairOpResponsePayload,
        CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CreateKeyPairOpResponsePayloadJsonDeserializer}.
   */
  public CreateKeyPairOpResponsePayloadJsonDeserializer() {
    super(CreateKeyPairOpResponsePayload.kmipTag, CreateKeyPairOpResponsePayload.encodingType);
  }

  @Override
  protected CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder createBuilder() {
    return CreateKeyPairOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER ->
          builder.privateKeyUniqueIdentifier(ctxt.readValue(p, PrivateKeyUniqueIdentifier.class));
      case KmipTag.Standard.PUBLIC_KEY_UNIQUE_IDENTIFIER ->
          builder.publicKeyUniqueIdentifier(ctxt.readValue(p, PublicKeyUniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateKeyPairOpResponsePayload build(
      CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
