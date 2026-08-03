package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.DerivationObjectLink;

/**
 * JSON deserializer for {@link DerivationObjectLink}.
 */
public class DerivationObjectLinkJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DerivationObjectLink,
        DerivationObjectLink.DerivationObjectLinkBuilder> {

  /**
   * Constructs a new {@link DerivationObjectLinkJsonDeserializer}.
   */
  public DerivationObjectLinkJsonDeserializer() {
    super(DerivationObjectLink.kmipTag, DerivationObjectLink.encodingType);
  }

  @Override
  protected DerivationObjectLink.DerivationObjectLinkBuilder createBuilder() {
    return DerivationObjectLink.builder();
  }

  @Override
  protected void setValue(DerivationObjectLink.DerivationObjectLinkBuilder builder, String tag,
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
  protected DerivationObjectLink build(DerivationObjectLink.DerivationObjectLinkBuilder builder) {
    return builder.build();
  }
}
