package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.CertifyOpRequestPayload;

public class CertifyOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertifyOpRequestPayload,
        CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder> {

  public CertifyOpRequestPayloadXmlDeserializer() {
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
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertifyOpRequestPayload build(
      CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}