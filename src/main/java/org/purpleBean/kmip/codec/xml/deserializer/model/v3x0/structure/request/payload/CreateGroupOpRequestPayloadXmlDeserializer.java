package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v3x0.structure.request.payload.CreateGroupOpRequestPayload;

public class CreateGroupOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CreateGroupOpRequestPayload,
        CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder> {

  public CreateGroupOpRequestPayloadXmlDeserializer() {
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