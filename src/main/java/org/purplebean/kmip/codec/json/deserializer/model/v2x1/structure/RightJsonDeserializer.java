package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;
import org.purplebean.kmip.model.v2x1.structure.Operations;
import org.purplebean.kmip.model.v2x1.structure.Right;

/**
 * JSON deserializer for {@link Right}.
 */
public class RightJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Right, Right.RightBuilder> {

  /**
   * Constructs a new {@link RightJsonDeserializer}.
   */
  public RightJsonDeserializer() {
    super(Right.kmipTag, Right.encodingType);
  }

  @Override
  protected Right.RightBuilder createBuilder() {
    return Right.builder();
  }

  @Override
  protected void setValue(Right.RightBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.USAGE_LIMITS ->
          builder.usageLimits(ctxt.readValue(p, UsageLimits.class));
      case KmipTag.Standard.OPERATIONS -> builder.operations(ctxt.readValue(p, Operations.class));
      case KmipTag.Standard.OBJECTS -> builder.managedObjects(
          ctxt.readValue(p, org.purplebean.kmip.model.v2x1.structure.Objects.class));
      case KmipTag.Standard.OBJECT_GROUPS ->
          builder.objectGroups(ctxt.readValue(p, ObjectGroups.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Right build(Right.RightBuilder builder) {
    return builder.build();
  }
}