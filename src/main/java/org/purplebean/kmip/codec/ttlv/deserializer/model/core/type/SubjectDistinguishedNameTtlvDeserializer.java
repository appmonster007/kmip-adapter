package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.SubjectDistinguishedName;

/**
 * TTLV deserializer for {@link SubjectDistinguishedName}.
 */
public class SubjectDistinguishedNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SubjectDistinguishedName,
        SubjectDistinguishedName.SubjectDistinguishedNameBuilder> {

  /**
   * Constructs a new {@link SubjectDistinguishedNameTtlvDeserializer}.
   */
  public SubjectDistinguishedNameTtlvDeserializer() {
    super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType);
  }

  @Override
  protected SubjectDistinguishedName.SubjectDistinguishedNameBuilder createBuilder() {
    return SubjectDistinguishedName.builder();
  }

  @Override
  protected void setValue(SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected SubjectDistinguishedName build(
      SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder) {
    return builder.build();
  }
}
