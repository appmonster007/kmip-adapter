package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateUserOpResponsePayload;

public class CreateUserOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateUserOpResponsePayload,
        CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder> {

  public CreateUserOpResponsePayloadTtlvDeserializer() {
    super(CreateUserOpResponsePayload.kmipTag, CreateUserOpResponsePayload.encodingType);
  }

  @Override
  protected CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder createBuilder() {
    return CreateUserOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateUserOpResponsePayload build(
      CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}