package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionTag;

public class AuthenticatedEncryptionTagTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AuthenticatedEncryptionTag,
        AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder> {

  public AuthenticatedEncryptionTagTtlvDeserializer() {
    super(AuthenticatedEncryptionTag.kmipTag, AuthenticatedEncryptionTag.encodingType);
  }

  @Override
  protected AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder createBuilder() {
    return AuthenticatedEncryptionTag.builder();
  }

  @Override
  protected void setValue(AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected AuthenticatedEncryptionTag build(
      AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder builder) {
    return builder.build();
  }
}