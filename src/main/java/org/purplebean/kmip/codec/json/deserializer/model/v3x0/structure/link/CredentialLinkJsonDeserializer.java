package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.structure.link.CredentialLink;

/**
 * JSON deserializer for {@link CredentialLink}.
 */
public class CredentialLinkJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CredentialLink, CredentialLink.CredentialLinkBuilder> {

  /**
   * Constructs a new {@link CredentialLinkJsonDeserializer}.
   */
  public CredentialLinkJsonDeserializer() {
    super(CredentialLink.kmipTag, CredentialLink.encodingType);
  }

  @Override
  protected CredentialLink.CredentialLinkBuilder createBuilder() {
    return CredentialLink.builder();
  }

  @Override
  protected void setValue(CredentialLink.CredentialLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CredentialLink build(CredentialLink.CredentialLinkBuilder builder) {
    return builder.build();
  }
}
