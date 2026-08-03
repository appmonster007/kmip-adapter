package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ReplacementObjectLink;

/**
 * JSON deserializer for {@link ReplacementObjectLink}.
 */
public class ReplacementObjectLinkJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ReplacementObjectLink,
        ReplacementObjectLink.ReplacementObjectLinkBuilder> {

  /**
   * Constructs a new {@link ReplacementObjectLinkJsonDeserializer}.
   */
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
