package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ModifyAttributeOpRequestPayload;

/**
 * XML deserializer for {@link ModifyAttributeOpRequestPayload}.
 */
public class ModifyAttributeOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ModifyAttributeOpRequestPayload,
        ModifyAttributeOpRequestPayload.ModifyAttributeOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ModifyAttributeOpRequestPayloadXmlDeserializer}.
   */
  public ModifyAttributeOpRequestPayloadXmlDeserializer() {
    super(ModifyAttributeOpRequestPayload.kmipTag, ModifyAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected ModifyAttributeOpRequestPayload.ModifyAttributeOpRequestPayloadBuilder createBuilder() {
    return ModifyAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      ModifyAttributeOpRequestPayload.ModifyAttributeOpRequestPayloadBuilder builder,
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
  protected ModifyAttributeOpRequestPayload build(
      ModifyAttributeOpRequestPayload.ModifyAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
