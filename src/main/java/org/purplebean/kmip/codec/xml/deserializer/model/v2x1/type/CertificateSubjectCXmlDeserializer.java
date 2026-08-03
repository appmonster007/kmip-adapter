package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectC;

/**
 * XML deserializer for {@link CertificateSubjectC}.
 */
public class CertificateSubjectCXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectC,
        CertificateSubjectC.CertificateSubjectCBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectCXmlDeserializer}.
   */
  public CertificateSubjectCXmlDeserializer() {
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