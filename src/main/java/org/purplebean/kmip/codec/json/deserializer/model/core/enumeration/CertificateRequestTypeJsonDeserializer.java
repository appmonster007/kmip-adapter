package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateRequestType,
        CertificateRequestType.CertificateRequestTypeBuilder> {

  public CertificateRequestTypeJsonDeserializer() {
    super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType);
  }

  @Override
  protected CertificateRequestType.CertificateRequestTypeBuilder createBuilder() {
    return CertificateRequestType.builder();
  }

  @Override
  protected void setValue(CertificateRequestType.CertificateRequestTypeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(CertificateRequestType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected CertificateRequestType build(
      CertificateRequestType.CertificateRequestTypeBuilder builder) {
    return builder.build();
  }
}
