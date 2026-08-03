package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ParentLink;

/**
 * XML deserializer for {@link ParentLink}.
 */
public class ParentLinkXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ParentLink, ParentLink.ParentLinkBuilder> {

  /**
   * Constructs a new {@link ParentLinkXmlDeserializer}.
   */
  public ParentLinkXmlDeserializer() {
    super(ParentLink.kmipTag, ParentLink.encodingType);
  }

  @Override
  protected ParentLink.ParentLinkBuilder createBuilder() {
    return ParentLink.builder();
  }

  @Override
  protected void setValue(ParentLink.ParentLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ParentLink build(ParentLink.ParentLinkBuilder builder) {
    return builder.build();
  }
}
