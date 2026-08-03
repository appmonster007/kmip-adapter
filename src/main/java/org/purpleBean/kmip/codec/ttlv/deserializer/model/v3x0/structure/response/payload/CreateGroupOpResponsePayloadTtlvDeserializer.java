package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.CreateGroupOpResponsePayload;

public class CreateGroupOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateGroupOpResponsePayload,
        CreateGroupOpResponsePayload.CreateGroupOpResponsePayloadBuilder> {

  public CreateGroupOpResponsePayloadTtlvDeserializer() {
    super(CreateGroupOpResponsePayload.kmipTag, CreateGroupOpResponsePayload.encodingType);
  }

  @Override
  protected CreateGroupOpResponsePayload.CreateGroupOpResponsePayloadBuilder createBuilder() {
    return CreateGroupOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CreateGroupOpResponsePayload.CreateGroupOpResponsePayloadBuilder builder,
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
  protected CreateGroupOpResponsePayload build(
      CreateGroupOpResponsePayload.CreateGroupOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}