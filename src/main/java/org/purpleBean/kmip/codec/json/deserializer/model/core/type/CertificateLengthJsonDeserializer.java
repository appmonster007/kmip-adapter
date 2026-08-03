package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CertificateLength;

public class CertificateLengthJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateLength,
        CertificateLength.CertificateLengthBuilder> {

  public CertificateLengthJsonDeserializer() {
    super(CertificateLength.kmipTag, CertificateLength.encodingType);
  }

  @Override
  protected CertificateLength.CertificateLengthBuilder createBuilder() {
    return CertificateLength.builder();
  }

  @Override
  protected void setValue(CertificateLength.CertificateLengthBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected CertificateLength build(CertificateLength.CertificateLengthBuilder builder) {
    return builder.build();
  }
}
