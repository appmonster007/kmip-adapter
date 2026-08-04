package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.model.v3x0.structure.request.payload.SetAttributeOpRequestPayload;

/**
 * XML deserializer for {@link SetAttributeOpRequestPayload}.
 */
public class SetAttributeOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SetAttributeOpRequestPayload,
        SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link SetAttributeOpRequestPayloadXmlDeserializer}.
   */
  public SetAttributeOpRequestPayloadXmlDeserializer() {
    super(SetAttributeOpRequestPayload.kmipTag, SetAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder createBuilder() {
    return SetAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.NEW_ATTRIBUTE ->
          builder.newAttribute(ctxt.readValue(p, NewAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetAttributeOpRequestPayload build(
      SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
