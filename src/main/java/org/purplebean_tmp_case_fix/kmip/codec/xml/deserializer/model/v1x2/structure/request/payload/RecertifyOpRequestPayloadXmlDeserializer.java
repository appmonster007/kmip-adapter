package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.core.type.Offset;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RecertifyOpRequestPayload;

public class RecertifyOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RecertifyOpRequestPayload,
        RecertifyOpRequestPayload.RecertifyOpRequestPayloadBuilder> {

  public RecertifyOpRequestPayloadXmlDeserializer() {
    super(RecertifyOpRequestPayload.kmipTag, RecertifyOpRequestPayload.encodingType);
  }

  @Override
  protected RecertifyOpRequestPayload.RecertifyOpRequestPayloadBuilder createBuilder() {
    return RecertifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RecertifyOpRequestPayload.RecertifyOpRequestPayloadBuilder builder,
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
      case KmipTag.Standard.OFFSET -> builder.offset(ctxt.readValue(p, Offset.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RecertifyOpRequestPayload build(
      RecertifyOpRequestPayload.RecertifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}