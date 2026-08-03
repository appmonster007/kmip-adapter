package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DeriveKeyOpResponsePayload;

public class DeriveKeyOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DeriveKeyOpResponsePayload,
        DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder> {

  public DeriveKeyOpResponsePayloadXmlDeserializer() {
    super(DeriveKeyOpResponsePayload.kmipTag, DeriveKeyOpResponsePayload.encodingType);
  }

  @Override
  protected DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder createBuilder() {
    return DeriveKeyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeriveKeyOpResponsePayload build(
      DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
