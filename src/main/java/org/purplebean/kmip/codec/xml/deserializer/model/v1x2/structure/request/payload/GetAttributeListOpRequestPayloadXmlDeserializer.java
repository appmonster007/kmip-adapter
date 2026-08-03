package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetAttributeListOpRequestPayload;

/**
 * XML deserializer for {@link GetAttributeListOpRequestPayload}.
 */
public class GetAttributeListOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<GetAttributeListOpRequestPayload,
        GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link GetAttributeListOpRequestPayloadXmlDeserializer}.
   */
  public GetAttributeListOpRequestPayloadXmlDeserializer() {
    super(GetAttributeListOpRequestPayload.kmipTag, GetAttributeListOpRequestPayload.encodingType);
  }

  @Override
  protected GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder
      createBuilder() {
    return GetAttributeListOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);

    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetAttributeListOpRequestPayload build(
      GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
