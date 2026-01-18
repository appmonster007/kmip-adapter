package org.purpleBean.kmip.codec.ttlv.deserializer.api.request;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RequestHeaderStructureTtlvDeserializer extends KmipDataTypeTtlvDeserializer<RequestHeaderStructure> {

    @Override
    public RequestHeaderStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return super.deserialize(ttlvBuffer, mapper);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, TtlvMapper mapper) {
        Class<? extends RequestHeaderStructure> headerClass = RequestHeaderStructure.getClassFromRegistry();

        if (headerClass == null) {
            headerClass = SimpleRequestHeader.class;
        }

        return headerClass;
    }
}