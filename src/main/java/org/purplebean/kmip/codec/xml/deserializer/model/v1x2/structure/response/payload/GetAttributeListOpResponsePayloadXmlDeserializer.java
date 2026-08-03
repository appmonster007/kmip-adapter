package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetAttributeListOpResponsePayload;

/**
 * XML deserializer for {@link GetAttributeListOpResponsePayload}.
 */
public class GetAttributeListOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<GetAttributeListOpResponsePayload,
        GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link GetAttributeListOpResponsePayloadXmlDeserializer}.
   */
  public GetAttributeListOpResponsePayloadXmlDeserializer() {
    super(GetAttributeListOpResponsePayload.kmipTag,
        GetAttributeListOpResponsePayload.encodingType);
  }

  @Override
  protected GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder
      createBuilder() {
    return GetAttributeListOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(ctxt.readValue(p, AttributeName.class));
      case KmipTag.Standard.ATTRIBUTE_REFERENCE ->
          builder.attributeReference(ctxt.readValue(p, KmipDataType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetAttributeListOpResponsePayload build(
      GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
