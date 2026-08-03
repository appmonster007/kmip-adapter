package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LocateOpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.LocatedItems;

/**
 * XML deserializer for {@link LocateOpResponsePayload}.
 */
public class LocateOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LocateOpResponsePayload,
        LocateOpResponsePayload.LocateOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link LocateOpResponsePayloadXmlDeserializer}.
   */
  public LocateOpResponsePayloadXmlDeserializer() {
    super(LocateOpResponsePayload.kmipTag, LocateOpResponsePayload.encodingType);
  }

  @Override
  protected LocateOpResponsePayload.LocateOpResponsePayloadBuilder createBuilder() {
    return LocateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LOCATED_ITEMS ->
          builder.locatedItems(ctxt.readValue(p, LocatedItems.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LocateOpResponsePayload build(
      LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
