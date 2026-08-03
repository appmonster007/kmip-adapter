package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;
import org.purplebean.kmip.model.core.structure.AlternativeName;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;

public class AlternativeNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AlternativeName, AlternativeName.AlternativeNameBuilder> {

  public AlternativeNameTtlvDeserializer() {
    super(AlternativeName.kmipTag, AlternativeName.encodingType);
  }

  @Override
  protected AlternativeName.AlternativeNameBuilder createBuilder() {
    return AlternativeName.builder();
  }

  @Override
  protected void setValue(AlternativeName.AlternativeNameBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ALTERNATIVE_NAME_TYPE ->
          builder.alternativeNameType(mapper.readValue(p, AlternativeNameType.class));
      case KmipTag.Standard.ALTERNATIVE_NAME_VALUE ->
          builder.alternativeNameValue(mapper.readValue(p, AlternativeNameValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AlternativeName build(AlternativeName.AlternativeNameBuilder builder) {
    return builder.build();
  }
}