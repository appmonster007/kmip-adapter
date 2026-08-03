package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectL;

/**
 * JSON deserializer for {@link CertificateSubjectL}.
 */
public class CertificateSubjectLJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateSubjectL,
        CertificateSubjectL.CertificateSubjectLBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectLJsonDeserializer}.
   */
  public CertificateSubjectLJsonDeserializer() {
    super(CertificateSubjectL.kmipTag, CertificateSubjectL.encodingType);
  }

  @Override
  protected CertificateSubjectL.CertificateSubjectLBuilder createBuilder() {
    return CertificateSubjectL.builder();
  }

  @Override
  protected void setValue(CertificateSubjectL.CertificateSubjectLBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectL build(CertificateSubjectL.CertificateSubjectLBuilder builder) {
    return builder.build();
  }
}