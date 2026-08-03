package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

public class CertificateRequestJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateRequest,
        CertificateRequest.CertificateRequestBuilder> {

  public CertificateRequestJsonDeserializer() {
    super(CertificateRequest.kmipTag, CertificateRequest.encodingType);
  }

  @Override
  protected CertificateRequest.CertificateRequestBuilder createBuilder() {
    return CertificateRequest.builder();
  }

  @Override
  protected void setValue(CertificateRequest.CertificateRequestBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected CertificateRequest build(CertificateRequest.CertificateRequestBuilder builder) {
    return builder.build();
  }
}
