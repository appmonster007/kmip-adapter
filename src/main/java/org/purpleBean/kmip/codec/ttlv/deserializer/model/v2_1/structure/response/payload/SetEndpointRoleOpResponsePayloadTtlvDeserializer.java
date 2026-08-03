package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetEndpointRoleOpResponsePayload;

public class SetEndpointRoleOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SetEndpointRoleOpResponsePayload,
        SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder> {

  public SetEndpointRoleOpResponsePayloadTtlvDeserializer() {
    super(SetEndpointRoleOpResponsePayload.kmipTag, SetEndpointRoleOpResponsePayload.encodingType);
  }

  @Override
  protected SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder createBuilder() {
    return SetEndpointRoleOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ENDPOINT_ROLE ->
          builder.endpointRole(mapper.readValue(p, EndpointRole.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetEndpointRoleOpResponsePayload build(
      SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}