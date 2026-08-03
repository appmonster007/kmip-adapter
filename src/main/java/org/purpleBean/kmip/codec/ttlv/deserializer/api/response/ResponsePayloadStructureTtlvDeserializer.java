package org.purpleBean.kmip.codec.ttlv.deserializer.api.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Operation;

public class ResponsePayloadStructureTtlvDeserializer
    extends KmipDataTypeTtlvDeserializer<ResponsePayloadStructure> {

  @Override
  public ResponsePayloadStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper)
      throws IOException {
    return super.deserialize(ttlvBuffer, mapper);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    String ctxtOperation = (String) mapper.getAttribute("operation");
    Operation.Value operationValue;
    if (ctxtOperation == null) {
      operationValue = null;
    } else {
      operationValue = Operation.fromName(ctxtOperation);
    }
    return ResponsePayloadStructure.getClassFromRegistry(operationValue);
  }
}
