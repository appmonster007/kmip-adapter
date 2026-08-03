package org.purplebean.kmip.codec.json.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestMessageStructure;
import org.purplebean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;

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
