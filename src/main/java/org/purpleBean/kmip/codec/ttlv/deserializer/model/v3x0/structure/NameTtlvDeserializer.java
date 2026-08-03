package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.model.v3x0.structure.Name;

public class NameTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Name, Name.NameBuilder> {

  public NameTtlvDeserializer() {
    super(Name.kmipTag, Name.encodingType);
  }

  @Override
  protected Name.NameBuilder createBuilder() {
    return Name.builder();
  }

  @Override
  protected void setValue(Name.NameBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.NAME_VALUE -> builder.nameValue(mapper.readValue(p, NameValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Name build(Name.NameBuilder builder) {
    return builder.build();
  }
}
