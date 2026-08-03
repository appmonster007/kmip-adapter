package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.LinkType;
import org.purplebean.kmip.model.core.structure.Link;
import org.purplebean.kmip.model.core.type.LinkedObjectIdentifier;

public class LinkJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Link, Link.LinkBuilder> {

  public LinkJsonDeserializer() {
    super(Link.kmipTag, Link.encodingType);
  }

  @Override
  protected Link.LinkBuilder createBuilder() {
    return Link.builder();
  }

  @Override
  protected void setValue(Link.LinkBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LINK_TYPE -> builder.linkType(ctxt.readValue(p, LinkType.class));
      case KmipTag.Standard.LINKED_OBJECT_IDENTIFIER ->
          builder.linkedObjectIdentifier(ctxt.readValue(p, LinkedObjectIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Link build(Link.LinkBuilder builder) {
    return builder.build();
  }
}
