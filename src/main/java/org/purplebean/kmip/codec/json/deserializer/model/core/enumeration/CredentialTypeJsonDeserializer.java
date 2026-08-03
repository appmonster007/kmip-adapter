package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.CredentialType;

/**
 * JSON deserializer for {@link CredentialType}.
 */
public class CredentialTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CredentialType, CredentialType.CredentialTypeBuilder> {

  /**
   * Constructs a new {@link CredentialTypeJsonDeserializer}.
   */
  public CredentialTypeJsonDeserializer() {
    super(CredentialType.kmipTag, CredentialType.encodingType);
  }

  @Override
  protected CredentialType.CredentialTypeBuilder createBuilder() {
    return CredentialType.builder();
  }

  @Override
  protected void setValue(CredentialType.CredentialTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(CredentialType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected CredentialType build(CredentialType.CredentialTypeBuilder builder) {
    return builder.build();
  }
}
