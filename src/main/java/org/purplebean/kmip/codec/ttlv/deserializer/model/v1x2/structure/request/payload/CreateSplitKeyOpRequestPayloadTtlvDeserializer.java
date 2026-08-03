package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.PrimeFieldSize;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateSplitKeyOpRequestPayload;

/**
 * TTLV deserializer for {@link CreateSplitKeyOpRequestPayload}.
 */
public class CreateSplitKeyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateSplitKeyOpRequestPayload,
        CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CreateSplitKeyOpRequestPayloadTtlvDeserializer}.
   */
  public CreateSplitKeyOpRequestPayloadTtlvDeserializer() {
    super(CreateSplitKeyOpRequestPayload.kmipTag, CreateSplitKeyOpRequestPayload.encodingType);
  }

  @Override
  protected CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder createBuilder() {
    return CreateSplitKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.SPLIT_KEY_PARTS ->
          builder.splitKeyParts(mapper.readValue(p, SplitKeyParts.class));
      case KmipTag.Standard.SPLIT_KEY_THRESHOLD ->
          builder.splitKeyThreshold(mapper.readValue(p, SplitKeyThreshold.class));
      case KmipTag.Standard.SPLIT_KEY_METHOD ->
          builder.splitKeyMethod(mapper.readValue(p, SplitKeyMethod.class));
      case KmipTag.Standard.PRIME_FIELD_SIZE ->
          builder.primeFieldSize(mapper.readValue(p, PrimeFieldSize.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateSplitKeyOpRequestPayload build(
      CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
