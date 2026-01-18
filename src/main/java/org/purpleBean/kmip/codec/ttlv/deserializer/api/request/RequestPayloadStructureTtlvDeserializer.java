package org.purpleBean.kmip.codec.ttlv.deserializer.api.request;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RequestPayloadStructureTtlvDeserializer extends KmipDataTypeTtlvDeserializer<RequestPayloadStructure> {

    @Override
    public RequestPayloadStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return super.deserialize(ttlvBuffer, mapper);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, TtlvMapper mapper) {
        String ctxtOperation = (String) mapper.getAttribute("operation");
        Operation.Value operationValue;
        if (ctxtOperation == null) {
            operationValue = null;
        } else {
            operationValue = Operation.fromName(ctxtOperation);
        }

        Class<? extends RequestPayloadStructure> payloadClass = RequestPayloadStructure.getClassFromRegistry(operationValue);

        if (payloadClass == null) {
            payloadClass = SimpleRequestPayload.class;
        }

        return payloadClass;
    }
}