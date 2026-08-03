package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CreateOpRequestPayload;

/**
 * TTLV deserializer for {@link CreateOpRequestPayload}.
 */
public class CreateOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateOpRequestPayload,
        CreateOpRequestPayload.CreateOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CreateOpRequestPayloadTtlvDeserializer}.
   */
  public CreateOpRequestPayloadTtlvDeserializer() {
    super(CreateOpRequestPayload.kmipTag, CreateOpRequestPayload.encodingType);
  }

  @Override
  protected CreateOpRequestPayload.CreateOpRequestPayloadBuilder createBuilder() {
    return CreateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateOpRequestPayload build(
      CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}