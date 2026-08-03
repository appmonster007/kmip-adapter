package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionTag;

public class AuthenticatedEncryptionTagJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AuthenticatedEncryptionTag,
        AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder> {

  public AuthenticatedEncryptionTagJsonDeserializer() {
    super(AuthenticatedEncryptionTag.kmipTag, AuthenticatedEncryptionTag.encodingType);
  }

  @Override
  protected AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder createBuilder() {
    return AuthenticatedEncryptionTag.builder();
  }

  @Override
  protected void setValue(AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected AuthenticatedEncryptionTag build(
      AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder builder) {
    return builder.build();
  }
}