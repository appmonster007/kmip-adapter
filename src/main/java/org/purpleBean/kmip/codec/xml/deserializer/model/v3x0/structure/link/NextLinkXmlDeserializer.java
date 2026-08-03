package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.NextLink;

public class NextLinkXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<NextLink, NextLink.NextLinkBuilder> {

  public NextLinkXmlDeserializer() {
    super(NextLink.kmipTag, NextLink.encodingType);
  }

  @Override
  protected NextLink.NextLinkBuilder createBuilder() {
    return NextLink.builder();
  }

  @Override
  protected void setValue(NextLink.NextLinkBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected NextLink build(NextLink.NextLinkBuilder builder) {
    return builder.build();
  }
}
