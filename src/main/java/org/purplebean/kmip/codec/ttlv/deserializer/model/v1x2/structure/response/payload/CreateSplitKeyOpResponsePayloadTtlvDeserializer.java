package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CreateSplitKeyOpResponsePayload;

/**
 * TTLV deserializer for {@link CreateSplitKeyOpResponsePayload}.
 */
public class CreateSplitKeyOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateSplitKeyOpResponsePayload,
        CreateSplitKeyOpResponsePayload.CreateSplitKeyOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CreateSplitKeyOpResponsePayloadTtlvDeserializer}.
   */
  public CreateSplitKeyOpResponsePayloadTtlvDeserializer() {
    super(CreateSplitKeyOpResponsePayload.kmipTag, CreateSplitKeyOpResponsePayload.encodingType);
  }

  @Override
  protected CreateSplitKeyOpResponsePayload.CreateSplitKeyOpResponsePayloadBuilder createBuilder() {
    return CreateSplitKeyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      CreateSplitKeyOpResponsePayload.CreateSplitKeyOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateSplitKeyOpResponsePayload build(
      CreateSplitKeyOpResponsePayload.CreateSplitKeyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
