package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateUserOpRequestPayload;

/**
 * TTLV deserializer for {@link CreateUserOpRequestPayload}.
 */
public class CreateUserOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateUserOpRequestPayload,
        CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CreateUserOpRequestPayloadTtlvDeserializer}.
   */
  public CreateUserOpRequestPayloadTtlvDeserializer() {
    super(CreateUserOpRequestPayload.kmipTag, CreateUserOpRequestPayload.encodingType);
  }

  @Override
  protected CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder createBuilder() {
    return CreateUserOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateUserOpRequestPayload build(
      CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}