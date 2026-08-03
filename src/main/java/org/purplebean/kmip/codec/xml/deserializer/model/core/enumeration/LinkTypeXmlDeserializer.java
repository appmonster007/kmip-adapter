package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.LinkType;

/**
 * XML deserializer for {@link LinkType}.
 */
public class LinkTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<LinkType, LinkType.LinkTypeBuilder> {

  /**
   * Constructs a new {@link LinkTypeXmlDeserializer}.
   */
  public LinkTypeXmlDeserializer() {
    super(LinkType.kmipTag, LinkType.encodingType);
  }

  @Override
  protected LinkType.LinkTypeBuilder createBuilder() {
    return LinkType.builder();
  }

  @Override
  protected void setValue(LinkType.LinkTypeBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(LinkType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected LinkType build(LinkType.LinkTypeBuilder builder) {
    return builder.build();
  }
}