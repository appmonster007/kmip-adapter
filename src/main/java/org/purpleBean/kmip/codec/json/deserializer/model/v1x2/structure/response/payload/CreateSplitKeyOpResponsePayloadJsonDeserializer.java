package org.purpleBean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.CreateSplitKeyOpResponsePayload;

public class CreateSplitKeyOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateSplitKeyOpResponsePayload,
        CreateSplitKeyOpResponsePayload.CreateSplitKeyOpResponsePayloadBuilder> {

  public CreateSplitKeyOpResponsePayloadJsonDeserializer() {
    super(CreateSplitKeyOpResponsePayload.kmipTag, CreateSplitKeyOpResponsePayload.encodingType);
  }

  @Override
  protected CreateSplitKeyOpResponsePayload.CreateSplitKeyOpResponsePayloadBuilder createBuilder() {
    return CreateSplitKeyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      CreateSplitKeyOpResponsePayload.CreateSplitKeyOpResponsePayloadBuilder builder, String tag,
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
  protected CreateSplitKeyOpResponsePayload build(
      CreateSplitKeyOpResponsePayload.CreateSplitKeyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
