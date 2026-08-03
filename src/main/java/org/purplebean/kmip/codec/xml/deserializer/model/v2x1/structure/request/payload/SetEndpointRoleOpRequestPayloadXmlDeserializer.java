package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetEndpointRoleOpRequestPayload;

/**
 * XML deserializer for {@link SetEndpointRoleOpRequestPayload}.
 */
public class SetEndpointRoleOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SetEndpointRoleOpRequestPayload,
        SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link SetEndpointRoleOpRequestPayloadXmlDeserializer}.
   */
  public SetEndpointRoleOpRequestPayloadXmlDeserializer() {
    super(SetEndpointRoleOpRequestPayload.kmipTag, SetEndpointRoleOpRequestPayload.encodingType);
  }

  @Override
  protected SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder createBuilder() {
    return SetEndpointRoleOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ENDPOINT_ROLE ->
          builder.endpointRole(ctxt.readValue(p, EndpointRole.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetEndpointRoleOpRequestPayload build(
      SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}