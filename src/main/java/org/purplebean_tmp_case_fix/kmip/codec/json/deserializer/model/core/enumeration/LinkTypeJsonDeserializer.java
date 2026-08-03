package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.LinkType;

public class LinkTypeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<LinkType, LinkType.LinkTypeBuilder> {

  public LinkTypeJsonDeserializer() {
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
