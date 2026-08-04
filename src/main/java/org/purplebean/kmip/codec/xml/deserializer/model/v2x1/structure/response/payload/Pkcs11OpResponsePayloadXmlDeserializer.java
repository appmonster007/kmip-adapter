package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.response.payload.Pkcs11OpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;
import org.purplebean.kmip.model.v2x1.type.Pkcs11ReturnCode;

/**
 * XML deserializer for {@link Pkcs11OpResponsePayload}.
 */
public class Pkcs11OpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs11OpResponsePayload,
        Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link Pkcs11OpResponsePayloadXmlDeserializer}.
   */
  public Pkcs11OpResponsePayloadXmlDeserializer() {
    super(Pkcs11OpResponsePayload.kmipTag, Pkcs11OpResponsePayload.encodingType);
  }

  @Override
  protected Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder createBuilder() {
    return Pkcs11OpResponsePayload.builder();
  }

  @Override
  protected void setValue(Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PKCS_11_RETURN_CODE ->
          builder.pkcs11ReturnCode(ctxt.readValue(p, Pkcs11ReturnCode.class));
      case KmipTag.Standard.PKCS_11_OUTPUT_PARAMETERS ->
          builder.pkcs11OutputParameters(ctxt.readValue(p, Pkcs11OutputParameters.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs11OpResponsePayload build(
      Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder builder) {
    return builder.build();
  }
}