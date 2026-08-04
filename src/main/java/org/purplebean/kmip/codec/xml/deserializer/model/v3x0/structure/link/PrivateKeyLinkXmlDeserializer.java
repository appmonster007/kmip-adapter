package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.structure.link.PrivateKeyLink;

/**
 * XML deserializer for {@link PrivateKeyLink}.
 */
public class PrivateKeyLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PrivateKeyLink, PrivateKeyLink.PrivateKeyLinkBuilder> {

  /**
   * Constructs a new {@link PrivateKeyLinkXmlDeserializer}.
   */
  public PrivateKeyLinkXmlDeserializer() {
    super(PrivateKeyLink.kmipTag, PrivateKeyLink.encodingType);
  }

  @Override
  protected PrivateKeyLink.PrivateKeyLinkBuilder createBuilder() {
    return PrivateKeyLink.builder();
  }

  @Override
  protected void setValue(PrivateKeyLink.PrivateKeyLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected PrivateKeyLink build(PrivateKeyLink.PrivateKeyLinkBuilder builder) {
    return builder.build();
  }
}
