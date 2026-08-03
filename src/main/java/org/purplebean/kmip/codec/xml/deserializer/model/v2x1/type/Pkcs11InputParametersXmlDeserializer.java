package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.Pkcs11InputParameters;

/**
 * XML deserializer for {@link Pkcs11InputParameters}.
 */
public class Pkcs11InputParametersXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs11InputParameters,
        Pkcs11InputParameters.Pkcs11InputParametersBuilder> {

  /**
   * Constructs a new {@link Pkcs11InputParametersXmlDeserializer}.
   */
  public Pkcs11InputParametersXmlDeserializer() {
    super(Pkcs11InputParameters.kmipTag, Pkcs11InputParameters.encodingType);
  }

  @Override
  protected Pkcs11InputParameters.Pkcs11InputParametersBuilder createBuilder() {
    return Pkcs11InputParameters.builder();
  }

  @Override
  protected void setValue(Pkcs11InputParameters.Pkcs11InputParametersBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected Pkcs11InputParameters build(
      Pkcs11InputParameters.Pkcs11InputParametersBuilder builder) {
    return builder.build();
  }
}