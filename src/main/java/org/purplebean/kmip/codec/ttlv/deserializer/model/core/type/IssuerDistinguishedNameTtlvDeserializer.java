package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;

/**
 * TTLV deserializer for {@link IssuerDistinguishedName}.
 */
public class IssuerDistinguishedNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<IssuerDistinguishedName,
        IssuerDistinguishedName.IssuerDistinguishedNameBuilder> {

  /**
   * Constructs a new {@link IssuerDistinguishedNameTtlvDeserializer}.
   */
  public IssuerDistinguishedNameTtlvDeserializer() {
    super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType);
  }

  @Override
  protected IssuerDistinguishedName.IssuerDistinguishedNameBuilder createBuilder() {
    return IssuerDistinguishedName.builder();
  }

  @Override
  protected void setValue(IssuerDistinguishedName.IssuerDistinguishedNameBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected IssuerDistinguishedName build(
      IssuerDistinguishedName.IssuerDistinguishedNameBuilder builder) {
    return builder.build();
  }
}
