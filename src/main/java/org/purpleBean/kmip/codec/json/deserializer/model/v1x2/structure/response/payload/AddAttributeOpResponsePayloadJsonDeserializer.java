package org.purpleBean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.AddAttributeOpResponsePayload;

public class AddAttributeOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AddAttributeOpResponsePayload,
        AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder> {

  public AddAttributeOpResponsePayloadJsonDeserializer() {
    super(AddAttributeOpResponsePayload.kmipTag, AddAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder createBuilder() {
    return AddAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AddAttributeOpResponsePayload build(
      AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
