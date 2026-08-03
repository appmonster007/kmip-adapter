package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CreateOpResponsePayload;

/**
 * JSON deserializer for {@link CreateOpResponsePayload}.
 */
public class CreateOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateOpResponsePayload,
        CreateOpResponsePayload.CreateOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CreateOpResponsePayloadJsonDeserializer}.
   */
  public CreateOpResponsePayloadJsonDeserializer() {
    super(CreateOpResponsePayload.kmipTag, CreateOpResponsePayload.encodingType);
  }

  @Override
  protected CreateOpResponsePayload.CreateOpResponsePayloadBuilder createBuilder() {
    return CreateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CreateOpResponsePayload.CreateOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
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
  protected CreateOpResponsePayload build(
      CreateOpResponsePayload.CreateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}