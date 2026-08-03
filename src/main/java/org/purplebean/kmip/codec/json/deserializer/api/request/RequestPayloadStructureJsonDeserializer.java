package org.purplebean.kmip.codec.json.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.Operation;

/**
 * JSON deserializer for {@link RequestPayloadStructure}.
 */
public class RequestPayloadStructureJsonDeserializer
    extends KmipDataTypeJsonDeserializer<RequestPayloadStructure> {

  @Override
  public RequestPayloadStructure deserialize(JsonParser p, DeserializationContext ctxt)
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
    return RequestPayloadStructure.getClassFromRegistry(operationValue);
  }
}