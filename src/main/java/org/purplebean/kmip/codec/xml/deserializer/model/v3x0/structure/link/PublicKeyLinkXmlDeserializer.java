package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.structure.link.PublicKeyLink;

/**
 * XML deserializer for {@link PublicKeyLink}.
 */
public class PublicKeyLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PublicKeyLink, PublicKeyLink.PublicKeyLinkBuilder> {

  /**
   * Constructs a new {@link PublicKeyLinkXmlDeserializer}.
   */
  public PublicKeyLinkXmlDeserializer() {
    super(PublicKeyLink.kmipTag, PublicKeyLink.encodingType);
  }

  @Override
  protected PublicKeyLink.PublicKeyLinkBuilder createBuilder() {
    return PublicKeyLink.builder();
  }

  @Override
  protected void setValue(PublicKeyLink.PublicKeyLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected PublicKeyLink build(PublicKeyLink.PublicKeyLinkBuilder builder) {
    return builder.build();
  }
}
