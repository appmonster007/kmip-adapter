package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.structure.link.PreviousLink;

/**
 * XML deserializer for {@link PreviousLink}.
 */
public class PreviousLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PreviousLink, PreviousLink.PreviousLinkBuilder> {

  /**
   * Constructs a new {@link PreviousLinkXmlDeserializer}.
   */
  public PreviousLinkXmlDeserializer() {
    super(PreviousLink.kmipTag, PreviousLink.encodingType);
  }

  @Override
  protected PreviousLink.PreviousLinkBuilder createBuilder() {
    return PreviousLink.builder();
  }

  @Override
  protected void setValue(PreviousLink.PreviousLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected PreviousLink build(PreviousLink.PreviousLinkBuilder builder) {
    return builder.build();
  }
}
