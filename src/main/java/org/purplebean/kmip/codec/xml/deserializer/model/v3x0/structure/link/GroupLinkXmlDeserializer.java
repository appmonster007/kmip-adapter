package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.structure.link.GroupLink;

/**
 * XML deserializer for {@link GroupLink}.
 */
public class GroupLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<GroupLink, GroupLink.GroupLinkBuilder> {

  /**
   * Constructs a new {@link GroupLinkXmlDeserializer}.
   */
  public GroupLinkXmlDeserializer() {
    super(GroupLink.kmipTag, GroupLink.encodingType);
  }

  @Override
  protected GroupLink.GroupLinkBuilder createBuilder() {
    return GroupLink.builder();
  }

  @Override
  protected void setValue(GroupLink.GroupLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected GroupLink build(GroupLink.GroupLinkBuilder builder) {
    return builder.build();
  }
}
