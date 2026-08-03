package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.AuthenticatedEncryptionTag;

public class AuthenticatedEncryptionTagXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AuthenticatedEncryptionTag,
        AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder> {

  public AuthenticatedEncryptionTagXmlDeserializer() {
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