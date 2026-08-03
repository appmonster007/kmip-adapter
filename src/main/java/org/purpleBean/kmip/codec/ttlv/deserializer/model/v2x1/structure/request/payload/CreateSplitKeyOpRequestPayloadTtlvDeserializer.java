package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.CreateSplitKeyOpRequestPayload;

public class CreateSplitKeyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateSplitKeyOpRequestPayload,
        CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder> {

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
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateSplitKeyOpRequestPayload build(
      CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}