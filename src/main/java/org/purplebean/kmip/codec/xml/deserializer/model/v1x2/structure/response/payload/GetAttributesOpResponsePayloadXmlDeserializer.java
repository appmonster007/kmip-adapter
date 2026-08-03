package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetAttributesOpResponsePayload;

/**
 * XML deserializer for {@link GetAttributesOpResponsePayload}.
 */
public class GetAttributesOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<GetAttributesOpResponsePayload,
        GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link GetAttributesOpResponsePayloadXmlDeserializer}.
   */
  public GetAttributesOpResponsePayloadXmlDeserializer() {
    super(GetAttributesOpResponsePayload.kmipTag, GetAttributesOpResponsePayload.encodingType);
  }

  @Override
  protected GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder createBuilder() {
    return GetAttributesOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder builder, String tag,
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
  protected GetAttributesOpResponsePayload build(
      GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
