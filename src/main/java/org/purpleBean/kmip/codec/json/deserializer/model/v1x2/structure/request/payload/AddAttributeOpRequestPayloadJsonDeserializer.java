package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.AddAttributeOpRequestPayload;

public class AddAttributeOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AddAttributeOpRequestPayload,
        AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder> {

  public AddAttributeOpRequestPayloadJsonDeserializer() {
    super(AddAttributeOpRequestPayload.kmipTag, AddAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder createBuilder() {
    return AddAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AddAttributeOpRequestPayload build(
      AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
