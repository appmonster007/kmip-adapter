package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionAdditionalData;

public class AuthenticatedEncryptionAdditionalDataJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AuthenticatedEncryptionAdditionalData,
        AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder> {

  public AuthenticatedEncryptionAdditionalDataJsonDeserializer() {
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