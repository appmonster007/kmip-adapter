package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;
import org.purplebean.kmip.model.v2x1.structure.request.payload.InteropOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.InteropIdentifier;

/**
 * XML deserializer for {@link InteropOpRequestPayload}.
 */
public class InteropOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<InteropOpRequestPayload,
        InteropOpRequestPayload.InteropOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link InteropOpRequestPayloadXmlDeserializer}.
   */
  public InteropOpRequestPayloadXmlDeserializer() {
    super(InteropOpRequestPayload.kmipTag, InteropOpRequestPayload.encodingType);
  }

  @Override
  protected InteropOpRequestPayload.InteropOpRequestPayloadBuilder createBuilder() {
    return InteropOpRequestPayload.builder();
  }

  @Override
  protected void setValue(InteropOpRequestPayload.InteropOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.INTEROP_FUNCTION ->
          builder.interopFunction(ctxt.readValue(p, InteropFunction.class));
      case KmipTag.Standard.INTEROP_IDENTIFIER ->
          builder.interopIdentifier(ctxt.readValue(p, InteropIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected InteropOpRequestPayload build(
      InteropOpRequestPayload.InteropOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}