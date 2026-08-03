package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetOpResponsePayload;

public class GetOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetOpResponsePayload,
        GetOpResponsePayload.GetOpResponsePayloadBuilder> {

  public GetOpResponsePayloadTtlvDeserializer() {
    super(GetOpResponsePayload.kmipTag, GetOpResponsePayload.encodingType);
  }

  @Override
  protected GetOpResponsePayload.GetOpResponsePayloadBuilder createBuilder() {
    return GetOpResponsePayload.builder();
  }

  @Override
  protected void setValue(GetOpResponsePayload.GetOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> {
        if (ManagedObject.isManagedObject(nodeTag)) {
          builder.object(mapper.readValue(p, ManagedObject.class));
        } else {
          throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
      }
    }
  }

  @Override
  protected GetOpResponsePayload build(GetOpResponsePayload.GetOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
