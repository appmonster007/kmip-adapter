package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.CreateUserOpResponsePayload;

/**
 * TTLV deserializer for {@link CreateUserOpResponsePayload}.
 */
public class CreateUserOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateUserOpResponsePayload,
        CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CreateUserOpResponsePayloadTtlvDeserializer}.
   */
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