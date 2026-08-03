package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2x1.structure.ObjectTypes;

public class ObjectTypesTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ObjectTypes, ObjectTypes.ObjectTypesBuilder> {

  public ObjectTypesTtlvDeserializer() {
    super(ObjectTypes.kmipTag, ObjectTypes.encodingType);
  }

  @Override
  protected ObjectTypes.ObjectTypesBuilder createBuilder() {
    return ObjectTypes.builder();
  }

  @Override
  protected void setValue(ObjectTypes.ObjectTypesBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObjectTypes build(ObjectTypes.ObjectTypesBuilder builder) {
    return builder.build();
  }
}