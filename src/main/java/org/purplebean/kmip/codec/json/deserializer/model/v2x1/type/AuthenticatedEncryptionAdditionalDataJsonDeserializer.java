package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionAdditionalData;

/**
 * JSON deserializer for {@link AuthenticatedEncryptionAdditionalData}.
 */
public class AuthenticatedEncryptionAdditionalDataJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AuthenticatedEncryptionAdditionalData,
        AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder> {

  /**
   * Constructs a new {@link AuthenticatedEncryptionAdditionalDataJsonDeserializer}.
   */
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