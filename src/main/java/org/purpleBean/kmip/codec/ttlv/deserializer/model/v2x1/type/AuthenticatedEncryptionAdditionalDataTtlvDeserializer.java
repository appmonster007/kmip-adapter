package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.AuthenticatedEncryptionAdditionalData;

public class AuthenticatedEncryptionAdditionalDataTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AuthenticatedEncryptionAdditionalData,
        AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder> {

  public AuthenticatedEncryptionAdditionalDataTtlvDeserializer() {
    super(AuthenticatedEncryptionAdditionalData.kmipTag,
        AuthenticatedEncryptionAdditionalData.encodingType);
  }

  @Override
  protected AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder createBuilder() {
    return AuthenticatedEncryptionAdditionalData.builder();
  }

  @Override
  protected void setValue(
      AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected AuthenticatedEncryptionAdditionalData build(
      AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder builder) {
    return builder.build();
  }
}