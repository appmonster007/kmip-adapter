package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.structure.link.NextLink;

/**
 * XML deserializer for {@link NextLink}.
 */
public class NextLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<NextLink, NextLink.NextLinkBuilder> {

  /**
   * Constructs a new {@link NextLinkXmlDeserializer}.
   */
  public NextLinkXmlDeserializer() {
    super(NextLink.kmipTag, NextLink.encodingType);
  }

  @Override
  protected NextLink.NextLinkBuilder createBuilder() {
    return NextLink.builder();
  }

  @Override
  protected void setValue(NextLink.NextLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected NextLink build(NextLink.NextLinkBuilder builder) {
    return builder.build();
  }
}
