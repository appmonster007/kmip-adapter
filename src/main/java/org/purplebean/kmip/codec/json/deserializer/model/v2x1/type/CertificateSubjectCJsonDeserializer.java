package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectC;

public class CertificateSubjectCJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateSubjectC,
        CertificateSubjectC.CertificateSubjectCBuilder> {

  public CertificateSubjectCJsonDeserializer() {
    super(CertificateSubjectC.kmipTag, CertificateSubjectC.encodingType);
  }

  @Override
  protected CertificateSubjectC.CertificateSubjectCBuilder createBuilder() {
    return CertificateSubjectC.builder();
  }

  @Override
  protected void setValue(CertificateSubjectC.CertificateSubjectCBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectC build(CertificateSubjectC.CertificateSubjectCBuilder builder) {
    return builder.build();
  }
}