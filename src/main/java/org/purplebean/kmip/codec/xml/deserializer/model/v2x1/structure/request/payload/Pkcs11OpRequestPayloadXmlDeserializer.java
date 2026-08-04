package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.structure.request.payload.Pkcs11OpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Pkcs11InputParameters;
import org.purplebean.kmip.model.v2x1.type.Pkcs11Interface;

/**
 * XML deserializer for {@link Pkcs11OpRequestPayload}.
 */
public class Pkcs11OpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs11OpRequestPayload,
        Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link Pkcs11OpRequestPayloadXmlDeserializer}.
   */
  public Pkcs11OpRequestPayloadXmlDeserializer() {
    super(Pkcs11OpRequestPayload.kmipTag, Pkcs11OpRequestPayload.encodingType);
  }

  @Override
  protected Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder createBuilder() {
    return Pkcs11OpRequestPayload.builder();
  }

  @Override
  protected void setValue(Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PKCS_11_INTERFACE ->
          builder.pkcs11Interface(ctxt.readValue(p, Pkcs11Interface.class));
      case KmipTag.Standard.PKCS_11_FUNCTION ->
          builder.pkcs11Function(ctxt.readValue(p, Pkcs11Function.class));
      case KmipTag.Standard.PKCS_11_INPUT_PARAMETERS ->
          builder.pkcs11InputParameters(ctxt.readValue(p, Pkcs11InputParameters.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs11OpRequestPayload build(
      Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder) {
    return builder.build();
  }
}