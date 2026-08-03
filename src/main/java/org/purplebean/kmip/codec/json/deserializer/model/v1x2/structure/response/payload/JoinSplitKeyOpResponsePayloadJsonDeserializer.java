package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.JoinSplitKeyOpResponsePayload;

/**
 * JSON deserializer for {@link JoinSplitKeyOpResponsePayload}.
 */
public class JoinSplitKeyOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<JoinSplitKeyOpResponsePayload,
        JoinSplitKeyOpResponsePayload.JoinSplitKeyOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link JoinSplitKeyOpResponsePayloadJsonDeserializer}.
   */
  public JoinSplitKeyOpResponsePayloadJsonDeserializer() {
    super(JoinSplitKeyOpResponsePayload.kmipTag, JoinSplitKeyOpResponsePayload.encodingType);
  }

  @Override
  protected JoinSplitKeyOpResponsePayload.JoinSplitKeyOpResponsePayloadBuilder createBuilder() {
    return JoinSplitKeyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      JoinSplitKeyOpResponsePayload.JoinSplitKeyOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected JoinSplitKeyOpResponsePayload build(
      JoinSplitKeyOpResponsePayload.JoinSplitKeyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
