package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;

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
