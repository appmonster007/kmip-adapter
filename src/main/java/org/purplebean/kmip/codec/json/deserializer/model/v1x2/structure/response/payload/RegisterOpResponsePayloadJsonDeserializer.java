package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RegisterOpResponsePayload;

/**
 * JSON deserializer for {@link RegisterOpResponsePayload}.
 */
public class RegisterOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RegisterOpResponsePayload,
        RegisterOpResponsePayload.RegisterOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link RegisterOpResponsePayloadJsonDeserializer}.
   */
  public RegisterOpResponsePayloadJsonDeserializer() {
    super(RegisterOpResponsePayload.kmipTag, RegisterOpResponsePayload.encodingType);
  }

  @Override
  protected RegisterOpResponsePayload.RegisterOpResponsePayloadBuilder createBuilder() {
    return RegisterOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RegisterOpResponsePayload.RegisterOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RegisterOpResponsePayload build(
      RegisterOpResponsePayload.RegisterOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}