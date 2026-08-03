package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.WrappingKeyLink;

public class WrappingKeyLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<WrappingKeyLink, WrappingKeyLink.WrappingKeyLinkBuilder> {

  public WrappingKeyLinkXmlDeserializer() {
    super(WrappingKeyLink.kmipTag, WrappingKeyLink.encodingType);
  }

  @Override
  protected WrappingKeyLink.WrappingKeyLinkBuilder createBuilder() {
    return WrappingKeyLink.builder();
  }

  @Override
  protected void setValue(WrappingKeyLink.WrappingKeyLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected WrappingKeyLink build(WrappingKeyLink.WrappingKeyLinkBuilder builder) {
    return builder.build();
  }
}
