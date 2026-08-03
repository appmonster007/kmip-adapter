package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.structure.request.payload.Pkcs11OpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.Pkcs11InputParameters;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;

public class Pkcs11OpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<Pkcs11OpRequestPayload,
        Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder> {

  public Pkcs11OpRequestPayloadJsonDeserializer() {
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
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.PKCS_11_FUNCTION ->
          builder.pkcs11Function(ctxt.readValue(p, Pkcs11Function.class));
      case KmipTag.Standard.PKCS_11_INPUT_PARAMETERS ->
          builder.pkcs11InputParameters(ctxt.readValue(p, Pkcs11InputParameters.class));
      case KmipTag.Standard.PKCS_11_OUTPUT_PARAMETERS ->
          builder.pkcs11OutputParameters(ctxt.readValue(p, Pkcs11OutputParameters.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs11OpRequestPayload build(
      Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder) {
    return builder.build();
  }
}