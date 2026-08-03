package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3x0.type.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PublicKeyUniqueIdentifier,
        PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder> {

  public PublicKeyUniqueIdentifierTtlvDeserializer() {
    super(PublicKeyUniqueIdentifier.kmipTag, PublicKeyUniqueIdentifier.encodingType);
  }

  @Override
  protected PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder createBuilder() {
    return PublicKeyUniqueIdentifier.builder();
  }

  @Override
  protected void setValue(PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected PublicKeyUniqueIdentifier build(
      PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder) {
    return builder.build();
  }
}
