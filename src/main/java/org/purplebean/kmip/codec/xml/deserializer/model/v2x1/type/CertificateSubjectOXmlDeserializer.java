package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectO;

/**
 * XML deserializer for {@link CertificateSubjectO}.
 */
public class CertificateSubjectOXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectO,
        CertificateSubjectO.CertificateSubjectOBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectOXmlDeserializer}.
   */
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