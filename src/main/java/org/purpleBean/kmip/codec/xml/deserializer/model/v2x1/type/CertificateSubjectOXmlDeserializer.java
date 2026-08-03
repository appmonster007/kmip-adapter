package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectO;

public class CertificateSubjectOXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectO,
        CertificateSubjectO.CertificateSubjectOBuilder> {

  public CertificateSubjectOXmlDeserializer() {
    super(CertificateSubjectO.kmipTag, CertificateSubjectO.encodingType);
  }

  @Override
  protected CertificateSubjectO.CertificateSubjectOBuilder createBuilder() {
    return CertificateSubjectO.builder();
  }

  @Override
  protected void setValue(CertificateSubjectO.CertificateSubjectOBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectO build(CertificateSubjectO.CertificateSubjectOBuilder builder) {
    return builder.build();
  }
}