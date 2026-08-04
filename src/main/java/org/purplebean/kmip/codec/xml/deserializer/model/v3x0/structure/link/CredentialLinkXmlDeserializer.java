package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.structure.link.CredentialLink;

/**
 * XML deserializer for {@link CredentialLink}.
 */
public class CredentialLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CredentialLink, CredentialLink.CredentialLinkBuilder> {

  /**
   * Constructs a new {@link CredentialLinkXmlDeserializer}.
   */
  public CredentialLinkXmlDeserializer() {
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
