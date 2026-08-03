package org.purplebean.kmip.codec.ttlv.deserializer.api.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponseMessageStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseMessage;

public class ResponseMessageStructureTtlvDeserializer
    extends KmipDataTypeTtlvDeserializer<ResponseMessageStructure> {

  @Override
  public ResponseMessageStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper)
      throws IOException {
    KmipSpec previous = KmipContext.getSpec();
    try {
      KmipContext.clear();
      SimpleResponseMessage simpleResponseMessage =
          mapper.readValue(ttlvBuffer, SimpleResponseMessage.class);
      ttlvBuffer.rewind();
      ProtocolVersion protocolVersion = simpleResponseMessage
          .getResponseHeader()
          .getProtocolVersion();

      KmipSpec spec = KmipSpec.fromValue(protocolVersion);
      KmipContext.setSpec(spec);
      return super.deserialize(ttlvBuffer, mapper);
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
                                                            TtlvMapper mapper) {
    return ResponseMessageStructure.getClassFromRegistry();
  }
}
