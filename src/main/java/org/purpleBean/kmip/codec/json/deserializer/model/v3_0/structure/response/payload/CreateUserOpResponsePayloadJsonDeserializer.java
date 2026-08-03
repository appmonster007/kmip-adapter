package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateUserOpResponsePayload;

public class CreateUserOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateUserOpResponsePayload,
        CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder> {

  public CreateUserOpResponsePayloadJsonDeserializer() {
    super(CreateUserOpResponsePayload.kmipTag, CreateUserOpResponsePayload.encodingType);
  }

  @Override
  protected CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder createBuilder() {
    return CreateUserOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateUserOpResponsePayload build(
      CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}