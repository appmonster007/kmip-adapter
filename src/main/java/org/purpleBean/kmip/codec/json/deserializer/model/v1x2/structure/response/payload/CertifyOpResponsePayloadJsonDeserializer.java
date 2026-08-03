package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CertifyOpResponsePayload;

public class CertifyOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertifyOpResponsePayload,
        CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder> {

  public CertifyOpResponsePayloadJsonDeserializer() {
    super(CertifyOpResponsePayload.kmipTag, CertifyOpResponsePayload.encodingType);
  }

  @Override
  protected CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder createBuilder() {
    return CertifyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder builder,
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
  protected CertifyOpResponsePayload build(
      CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}