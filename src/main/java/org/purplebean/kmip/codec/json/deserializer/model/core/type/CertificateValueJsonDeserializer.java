package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CertificateValue;

/**
 * JSON deserializer for {@link CertificateValue}.
 */
public class CertificateValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateValue,
        CertificateValue.CertificateValueBuilder> {

  /**
   * Constructs a new {@link CertificateValueJsonDeserializer}.
   */
  public CertificateValueJsonDeserializer() {
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
