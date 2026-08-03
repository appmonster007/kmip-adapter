package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.CreateOpRequestPayload;

public class CreateOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateOpRequestPayload,
        CreateOpRequestPayload.CreateOpRequestPayloadBuilder> {

  public CreateOpRequestPayloadJsonDeserializer() {
    super(CreateOpRequestPayload.kmipTag, CreateOpRequestPayload.encodingType);
  }

  @Override
  protected CreateOpRequestPayload.CreateOpRequestPayloadBuilder createBuilder() {
    return CreateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateOpRequestPayload build(
      CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}