package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.enumeration.AdjustmentType;
import org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.AdjustAttributeOpRequestPayload;

public class AdjustAttributeOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AdjustAttributeOpRequestPayload,
        AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder> {

  public AdjustAttributeOpRequestPayloadXmlDeserializer() {
    super(AdjustAttributeOpRequestPayload.kmipTag, AdjustAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder createBuilder() {
    return AdjustAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CURRENT_ATTRIBUTE ->
          builder.currentAttribute(ctxt.readValue(p, CurrentAttribute.class));
      case KmipTag.Standard.ADJUSTMENT_TYPE ->
          builder.adjustmentType(ctxt.readValue(p, AdjustmentType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AdjustAttributeOpRequestPayload build(
      AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}