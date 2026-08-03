package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.CreateOpResponsePayload;

/**
 * XML deserializer for {@link CreateOpResponsePayload}.
 */
public class CreateOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CreateOpResponsePayload,
        CreateOpResponsePayload.CreateOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CreateOpResponsePayloadXmlDeserializer}.
   */
  public CreateOpResponsePayloadXmlDeserializer() {
    super(CreateOpResponsePayload.kmipTag, CreateOpResponsePayload.encodingType);
  }

  @Override
  protected CreateOpResponsePayload.CreateOpResponsePayloadBuilder createBuilder() {
    return CreateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CreateOpResponsePayload.CreateOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateOpResponsePayload build(
      CreateOpResponsePayload.CreateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
