package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.model.core.structure.Name;
import org.purplebean.kmip.model.core.type.NameValue;

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
      case KmipTag.Standard.NAME_TYPE -> builder.nameType(mapper.readValue(p, NameType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Name build(Name.NameBuilder builder) {
    return builder.build();
  }
}