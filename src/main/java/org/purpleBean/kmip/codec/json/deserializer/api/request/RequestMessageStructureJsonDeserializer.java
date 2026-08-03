package org.purpleBean.kmip.codec.json.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestMessageStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;

public class RequestMessageStructureJsonDeserializer
    extends KmipDataTypeJsonDeserializer<RequestMessageStructure> {

  @Override
  public RequestMessageStructure deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    TokenBuffer buffer = new TokenBuffer(p, ctxt);
    buffer.copyCurrentStructure(p);

    KmipSpec previous = KmipContext.getSpec();
    try {
      KmipContext.clear();
      JsonParser replay = buffer.asParser();
      replay.nextToken();
      RequestMessageStructure requestMessage = super.deserialize(replay, ctxt);
      ProtocolVersion protocolVersion = requestMessage
          .getRequestHeader()
          .getProtocolVersion();

      KmipSpec spec = KmipSpec.fromValue(protocolVersion);
      KmipContext.setSpec(spec);
      JsonParser original = buffer.asParser();
      original.nextToken();
      return super.deserialize(original, ctxt);
    } finally {
      if (previous != null) {
        KmipContext.setSpec(previous);
      } else {
        KmipContext.clear();
      }
    }
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    return RequestMessageStructure.getClassFromRegistry();
  }
}
