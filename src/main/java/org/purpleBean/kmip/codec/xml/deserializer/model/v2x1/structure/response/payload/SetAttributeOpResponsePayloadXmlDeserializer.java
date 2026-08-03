package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetAttributeOpResponsePayload;

public class SetAttributeOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SetAttributeOpResponsePayload,
        SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder> {

  public SetAttributeOpResponsePayloadXmlDeserializer() {
    super(SetAttributeOpResponsePayload.kmipTag, SetAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder createBuilder() {
    return SetAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetAttributeOpResponsePayload build(
      SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}