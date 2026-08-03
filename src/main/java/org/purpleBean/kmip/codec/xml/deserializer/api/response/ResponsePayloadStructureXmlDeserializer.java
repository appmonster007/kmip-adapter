package org.purpleBean.kmip.codec.xml.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;

public class ResponsePayloadStructureXmlDeserializer
    extends KmipDataTypeXmlDeserializer<ResponsePayloadStructure> {

  @Override
  public ResponsePayloadStructure deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return super.deserialize(p, ctxt);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    String ctxtOperation = (String) ctxt.getAttribute("operation");
    Operation.Value operationValue;
    if (ctxtOperation == null) {
      operationValue = null;
    } else {
      operationValue = Operation.fromName(ctxtOperation);
    }
    return ResponsePayloadStructure.getClassFromRegistry(operationValue);
  }
}
