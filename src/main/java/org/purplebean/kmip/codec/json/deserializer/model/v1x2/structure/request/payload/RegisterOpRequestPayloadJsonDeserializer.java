package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RegisterOpRequestPayload;

/**
 * JSON deserializer for {@link RegisterOpRequestPayload}.
 */
public class RegisterOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RegisterOpRequestPayload,
        RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link RegisterOpRequestPayloadJsonDeserializer}.
   */
  public RegisterOpRequestPayloadJsonDeserializer() {
    super(RegisterOpRequestPayload.kmipTag, RegisterOpRequestPayload.encodingType);
  }

  @Override
  protected RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder createBuilder() {
    return RegisterOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> {
        ObjectType objectType = ctxt.readValue(p, ObjectType.class);
        ctxt.setAttribute("objectType", objectType.getDescription());
        builder.objectType(objectType);
      }
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> {
        if (ManagedObject.isManagedObject(nodeTag)) {
          builder.object(ctxt.readValue(p, ManagedObject.class));
        } else {
          throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
      }
    }
  }

  @Override
  protected RegisterOpRequestPayload build(
      RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}