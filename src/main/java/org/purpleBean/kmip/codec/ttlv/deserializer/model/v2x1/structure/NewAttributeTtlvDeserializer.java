package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.structure.NewAttribute;

public class NewAttributeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<NewAttribute, NewAttribute.NewAttributeBuilder> {

  public NewAttributeTtlvDeserializer() {
    super(NewAttribute.kmipTag, NewAttribute.encodingType);
  }

  @Override
  protected NewAttribute.NewAttributeBuilder createBuilder() {
    return NewAttribute.builder();
  }

  @Override
  protected void setValue(NewAttribute.NewAttributeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    // TTLV layout: [tag:3][type:1][length:4][value:n] — type byte is at offset 3
    ByteBuffer view = p.asReadOnlyBuffer();
    view.rewind();
    view.position(3);
    EncodingType childEncodingType = EncodingType
        .fromTypeValue(view.get())
        .orElseThrow(
            () -> new IllegalArgumentException("Unknown encoding type in NewAttribute child TTLV"));
    Class<? extends KmipDataType> clazz = getKmipDataTypeClass(nodeTag, childEncodingType, mapper);
    builder.attribute((KmipAttribute) mapper.readValue(p, clazz));
  }

  @Override
  protected NewAttribute build(NewAttribute.NewAttributeBuilder builder) {
    return builder.build();
  }
}
