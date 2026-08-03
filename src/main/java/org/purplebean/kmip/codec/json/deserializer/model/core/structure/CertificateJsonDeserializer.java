package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.CertificateValue;

public class CertificateJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Certificate, Certificate.CertificateBuilder> {

  public CertificateJsonDeserializer() {
    super(Certificate.kmipTag, Certificate.encodingType);
  }

  @Override
  protected Certificate.CertificateBuilder createBuilder() {
    return Certificate.builder();
  }

  @Override
  protected void setValue(Certificate.CertificateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CERTIFICATE_TYPE ->
          builder.certificateType(ctxt.readValue(p, CertificateType.class));
      case KmipTag.Standard.CERTIFICATE_VALUE ->
          builder.certificateValue(ctxt.readValue(p, CertificateValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Certificate build(Certificate.CertificateBuilder builder) {
    return builder.build();
  }
}
