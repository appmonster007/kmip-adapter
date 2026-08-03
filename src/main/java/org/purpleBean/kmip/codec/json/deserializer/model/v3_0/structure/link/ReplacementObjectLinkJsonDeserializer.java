package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacementObjectLink;

public class ReplacementObjectLinkJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ReplacementObjectLink,
        ReplacementObjectLink.ReplacementObjectLinkBuilder> {

  public ReplacementObjectLinkJsonDeserializer() {
    super(ReplacementObjectLink.kmipTag, ReplacementObjectLink.encodingType);
  }

  @Override
  protected ReplacementObjectLink.ReplacementObjectLinkBuilder createBuilder() {
    return ReplacementObjectLink.builder();
  }

  @Override
  protected void setValue(ReplacementObjectLink.ReplacementObjectLinkBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReplacementObjectLink build(
      ReplacementObjectLink.ReplacementObjectLinkBuilder builder) {
    return builder.build();
  }
}
