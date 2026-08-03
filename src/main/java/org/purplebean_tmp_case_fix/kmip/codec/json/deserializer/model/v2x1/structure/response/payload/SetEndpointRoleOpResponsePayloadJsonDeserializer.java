package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetEndpointRoleOpResponsePayload;

public class SetEndpointRoleOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SetEndpointRoleOpResponsePayload,
        SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder> {

  public SetEndpointRoleOpResponsePayloadJsonDeserializer() {
    super(SetEndpointRoleOpResponsePayload.kmipTag, SetEndpointRoleOpResponsePayload.encodingType);
  }

  @Override
  protected SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder createBuilder() {
    return SetEndpointRoleOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ENDPOINT_ROLE ->
          builder.endpointRole(ctxt.readValue(p, EndpointRole.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetEndpointRoleOpResponsePayload build(
      SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}