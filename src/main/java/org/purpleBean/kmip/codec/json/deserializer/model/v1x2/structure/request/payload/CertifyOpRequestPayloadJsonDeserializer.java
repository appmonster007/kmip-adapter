package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CertifyOpRequestPayload;

public class CertifyOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertifyOpRequestPayload,
        CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder> {

  public CertifyOpRequestPayloadJsonDeserializer() {
    super(CertifyOpRequestPayload.kmipTag, CertifyOpRequestPayload.encodingType);
  }

  @Override
  protected CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder createBuilder() {
    return CertifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CERTIFICATE_REQUEST_TYPE ->
          builder.certificateRequestType(ctxt.readValue(p, CertificateRequestType.class));
      case KmipTag.Standard.CERTIFICATE_REQUEST ->
          builder.certificateRequest(ctxt.readValue(p, CertificateRequest.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertifyOpRequestPayload build(
      CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}