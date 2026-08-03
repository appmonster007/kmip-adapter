package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.structure.OpaqueObject;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

public class OpaqueObjectTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OpaqueObject, OpaqueObject.OpaqueObjectBuilder> {

  public OpaqueObjectTtlvDeserializer() {
    super(OpaqueObject.kmipTag, OpaqueObject.encodingType);
  }

  @Override
  protected OpaqueObject.OpaqueObjectBuilder createBuilder() {
    return OpaqueObject.builder();
  }

  @Override
  protected void setValue(OpaqueObject.OpaqueObjectBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPAQUE_DATA_TYPE ->
          builder.opaqueDataType(mapper.readValue(p, OpaqueDataType.class));
      case KmipTag.Standard.OPAQUE_DATA_VALUE ->
          builder.opaqueDataValue(mapper.readValue(p, OpaqueDataValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected OpaqueObject build(OpaqueObject.OpaqueObjectBuilder builder) {
    return builder.build();
  }
}