package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionAdditionalData;

public class AuthenticatedEncryptionAdditionalDataXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AuthenticatedEncryptionAdditionalData,
        AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder> {

  public AuthenticatedEncryptionAdditionalDataXmlDeserializer() {
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
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected AuthenticatedEncryptionAdditionalData build(
      AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder builder) {
    return builder.build();
  }
}