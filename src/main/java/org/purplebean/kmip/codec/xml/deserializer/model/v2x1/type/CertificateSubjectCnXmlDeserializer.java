package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectCn;

/**
 * XML deserializer for {@link CertificateSubjectCn}.
 */
public class CertificateSubjectCnXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectCn,
        CertificateSubjectCn.CertificateSubjectCnBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectCnXmlDeserializer}.
   */
  public CertificateSubjectCnXmlDeserializer() {
    super(CertificateSubjectCn.kmipTag, CertificateSubjectCn.encodingType);
  }

  @Override
  protected CertificateSubjectCn.CertificateSubjectCnBuilder createBuilder() {
    return CertificateSubjectCn.builder();
  }

  @Override
  protected void setValue(CertificateSubjectCn.CertificateSubjectCnBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectCn build(CertificateSubjectCn.CertificateSubjectCnBuilder builder) {
    return builder.build();
  }
}