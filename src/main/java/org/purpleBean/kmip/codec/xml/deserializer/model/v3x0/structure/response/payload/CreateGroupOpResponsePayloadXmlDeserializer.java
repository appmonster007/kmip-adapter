package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.response.payload.CreateGroupOpResponsePayload;

public class CreateGroupOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CreateGroupOpResponsePayload,
        CreateGroupOpResponsePayload.CreateGroupOpResponsePayloadBuilder> {

  public CreateGroupOpResponsePayloadXmlDeserializer() {
    super(CreateGroupOpResponsePayload.kmipTag, CreateGroupOpResponsePayload.encodingType);
  }

  @Override
  protected CreateGroupOpResponsePayload.CreateGroupOpResponsePayloadBuilder createBuilder() {
    return CreateGroupOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CreateGroupOpResponsePayload.CreateGroupOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateGroupOpResponsePayload build(
      CreateGroupOpResponsePayload.CreateGroupOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}