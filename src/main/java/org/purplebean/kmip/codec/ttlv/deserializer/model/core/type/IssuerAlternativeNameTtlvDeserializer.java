package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;

/**
 * TTLV deserializer for {@link IssuerAlternativeName}.
 */
public class IssuerAlternativeNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<IssuerAlternativeName,
        IssuerAlternativeName.IssuerAlternativeNameBuilder> {

  /**
   * Constructs a new {@link IssuerAlternativeNameTtlvDeserializer}.
   */
  public IssuerAlternativeNameTtlvDeserializer() {
    super(IssuerAlternativeName.kmipTag, IssuerAlternativeName.encodingType);
  }

  @Override
  protected IssuerAlternativeName.IssuerAlternativeNameBuilder createBuilder() {
    return IssuerAlternativeName.builder();
  }

  @Override
  protected void setValue(IssuerAlternativeName.IssuerAlternativeNameBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected IssuerAlternativeName build(
      IssuerAlternativeName.IssuerAlternativeNameBuilder builder) {
    return builder.build();
  }
}
