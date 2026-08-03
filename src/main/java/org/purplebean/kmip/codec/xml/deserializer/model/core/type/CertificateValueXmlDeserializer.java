package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.CertificateValue;

/**
 * XML deserializer for {@link CertificateValue}.
 */
public class CertificateValueXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateValue,
        CertificateValue.CertificateValueBuilder> {

  /**
   * Constructs a new {@link CertificateValueXmlDeserializer}.
   */
  public CertificateValueXmlDeserializer() {
    super(CertificateValue.kmipTag, CertificateValue.encodingType);
  }

  @Override
  protected CertificateValue.CertificateValueBuilder createBuilder() {
    return CertificateValue.builder();
  }

  @Override
  protected void setValue(CertificateValue.CertificateValueBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected CertificateValue build(CertificateValue.CertificateValueBuilder builder) {
    return builder.build();
  }
}