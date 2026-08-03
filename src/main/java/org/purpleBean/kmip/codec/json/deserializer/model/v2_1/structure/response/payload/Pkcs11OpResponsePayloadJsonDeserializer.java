package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.Pkcs11OpResponsePayload;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11OutputParameters;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11ReturnCode;

public class Pkcs11OpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<Pkcs11OpResponsePayload,
        Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder> {

  public Pkcs11OpResponsePayloadJsonDeserializer() {
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
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs11OpResponsePayload build(
      Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder builder) {
    return builder.build();
  }
}