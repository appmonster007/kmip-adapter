package org.purpleBean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.MacOpRequestPayload;

public class MacOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MacOpRequestPayload,
        MacOpRequestPayload.MacOpRequestPayloadBuilder> {

  public MacOpRequestPayloadJsonDeserializer() {
    super(MacOpRequestPayload.kmipTag, MacOpRequestPayload.encodingType);
  }

  @Override
  protected MacOpRequestPayload.MacOpRequestPayloadBuilder createBuilder() {
    return MacOpRequestPayload.builder();
  }

  @Override
  protected void setValue(MacOpRequestPayload.MacOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacOpRequestPayload build(MacOpRequestPayload.MacOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}