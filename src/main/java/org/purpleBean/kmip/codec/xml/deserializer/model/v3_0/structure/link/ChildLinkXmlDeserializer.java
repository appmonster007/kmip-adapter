package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.ChildLink;

public class ChildLinkXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ChildLink, ChildLink.ChildLinkBuilder> {

  public ChildLinkXmlDeserializer() {
    super(ChildLink.kmipTag, ChildLink.encodingType);
  }

  @Override
  protected ChildLink.ChildLinkBuilder createBuilder() {
    return ChildLink.builder();
  }

  @Override
  protected void setValue(ChildLink.ChildLinkBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ChildLink build(ChildLink.ChildLinkBuilder builder) {
    return builder.build();
  }
}
