package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.CreateCredentialOpResponsePayload;

/**
 * JSON deserializer for {@link CreateCredentialOpResponsePayload}.
 */
public class CreateCredentialOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateCredentialOpResponsePayload,
        CreateCredentialOpResponsePayload.CreateCredentialOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CreateCredentialOpResponsePayloadJsonDeserializer}.
   */
  public CreateCredentialOpResponsePayloadJsonDeserializer() {
    super(CreateCredentialOpResponsePayload.kmipTag,
        CreateCredentialOpResponsePayload.encodingType);
  }

  @Override
  protected CreateCredentialOpResponsePayload.CreateCredentialOpResponsePayloadBuilder
      createBuilder() {
    return CreateCredentialOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      CreateCredentialOpResponsePayload.CreateCredentialOpResponsePayloadBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateCredentialOpResponsePayload build(
      CreateCredentialOpResponsePayload.CreateCredentialOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}