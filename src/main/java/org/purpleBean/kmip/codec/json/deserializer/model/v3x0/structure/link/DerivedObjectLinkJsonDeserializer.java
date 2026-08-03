package org.purpleBean.kmip.codec.json.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.DerivedObjectLink;

public class DerivedObjectLinkJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DerivedObjectLink,
        DerivedObjectLink.DerivedObjectLinkBuilder> {

  public DerivedObjectLinkJsonDeserializer() {
    super(DerivedObjectLink.kmipTag, DerivedObjectLink.encodingType);
  }

  @Override
  protected DerivedObjectLink.DerivedObjectLinkBuilder createBuilder() {
    return DerivedObjectLink.builder();
  }

  @Override
  protected void setValue(DerivedObjectLink.DerivedObjectLinkBuilder builder, String tag,
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
  protected DerivedObjectLink build(DerivedObjectLink.DerivedObjectLinkBuilder builder) {
    return builder.build();
  }
}
