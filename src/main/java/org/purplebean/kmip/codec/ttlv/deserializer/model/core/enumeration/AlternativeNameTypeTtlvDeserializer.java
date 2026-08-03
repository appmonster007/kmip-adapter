package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AlternativeNameType,
        AlternativeNameType.AlternativeNameTypeBuilder> {

  public AlternativeNameTypeTtlvDeserializer() {
    super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType);
  }

  @Override
  protected AlternativeNameType.AlternativeNameTypeBuilder createBuilder() {
    return AlternativeNameType.builder();
  }

  @Override
  protected void setValue(AlternativeNameType.AlternativeNameTypeBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(AlternativeNameType.fromValue(value));
  }

  @Override
  protected AlternativeNameType build(AlternativeNameType.AlternativeNameTypeBuilder builder) {
    return builder.build();
  }
}
