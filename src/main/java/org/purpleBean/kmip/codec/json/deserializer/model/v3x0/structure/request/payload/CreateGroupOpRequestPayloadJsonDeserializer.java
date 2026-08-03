package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateGroupOpRequestPayload;

public class CreateGroupOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateGroupOpRequestPayload,
        CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder> {

  public CreateGroupOpRequestPayloadJsonDeserializer() {
    super(CreateGroupOpRequestPayload.kmipTag, CreateGroupOpRequestPayload.encodingType);
  }

  @Override
  protected CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder createBuilder() {
    return CreateGroupOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateGroupOpRequestPayload build(
      CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}