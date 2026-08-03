package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateUserOpRequestPayload;

public class CreateUserOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CreateUserOpRequestPayload,
        CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder> {

  public CreateUserOpRequestPayloadXmlDeserializer() {
    super(CreateUserOpRequestPayload.kmipTag, CreateUserOpRequestPayload.encodingType);
  }

  @Override
  protected CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder createBuilder() {
    return CreateUserOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateUserOpRequestPayload build(
      CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}