package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.SubjectAlternativeName;

public class SubjectAlternativeNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SubjectAlternativeName,
        SubjectAlternativeName.SubjectAlternativeNameBuilder> {

  public SubjectAlternativeNameTtlvDeserializer() {
    super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType);
  }

  @Override
  protected SubjectAlternativeName.SubjectAlternativeNameBuilder createBuilder() {
    return SubjectAlternativeName.builder();
  }

  @Override
  protected void setValue(SubjectAlternativeName.SubjectAlternativeNameBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected SubjectAlternativeName build(
      SubjectAlternativeName.SubjectAlternativeNameBuilder builder) {
    return builder.build();
  }
}
