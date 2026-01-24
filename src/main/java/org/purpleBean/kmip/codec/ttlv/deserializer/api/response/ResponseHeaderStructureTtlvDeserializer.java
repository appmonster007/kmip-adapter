package org.purpleBean.kmip.codec.ttlv.deserializer.api.response;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseHeaderStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ResponseHeaderStructureTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ResponseHeaderStructure> {

    @Override
    public ResponseHeaderStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return super.deserialize(ttlvBuffer, mapper);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, TtlvMapper mapper) {
        Class<? extends ResponseHeaderStructure> headerClass = ResponseHeaderStructure.getClassFromRegistry();
        if (headerClass == null && KmipContext.getSpec().equals(KmipSpec.UnknownVersion)) {
            headerClass = SimpleResponseHeader.class;
        }
        return headerClass;
    }
}
