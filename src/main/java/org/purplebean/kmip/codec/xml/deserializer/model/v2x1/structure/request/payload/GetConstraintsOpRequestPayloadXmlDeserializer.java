package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.GetConstraintsOpRequestPayload;

/**
 * XML deserializer for {@link GetConstraintsOpRequestPayload}.
 */
public class GetConstraintsOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<GetConstraintsOpRequestPayload,
        GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link GetConstraintsOpRequestPayloadXmlDeserializer}.
   */
  public GetConstraintsOpRequestPayloadXmlDeserializer() {
    super(GetConstraintsOpRequestPayload.kmipTag, GetConstraintsOpRequestPayload.encodingType);
  }

  @Override
  protected GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder createBuilder() {
    return GetConstraintsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetConstraintsOpRequestPayload build(
      GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}