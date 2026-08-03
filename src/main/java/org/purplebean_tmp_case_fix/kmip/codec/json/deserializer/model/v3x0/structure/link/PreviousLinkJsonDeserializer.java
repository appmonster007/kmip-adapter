package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.PreviousLink;

public class PreviousLinkJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<PreviousLink, PreviousLink.PreviousLinkBuilder> {

  public PreviousLinkJsonDeserializer() {
    super(PreviousLink.kmipTag, PreviousLink.encodingType);
  }

  @Override
  protected PreviousLink.PreviousLinkBuilder createBuilder() {
    return PreviousLink.builder();
  }

  @Override
  protected void setValue(PreviousLink.PreviousLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PreviousLink build(PreviousLink.PreviousLinkBuilder builder) {
    return builder.build();
  }
}
