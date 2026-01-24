package org.purpleBean.kmip.codec.ttlv.deserializer.api;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ManagedObjectTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ManagedObject> {

    @Override
    public ManagedObject deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return super.deserialize(ttlvBuffer, mapper);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, TtlvMapper mapper) {
        String ctxtObjectType = (String) mapper.getAttribute("objectType");
        ObjectType.Value objectTypeValue;

        if (ctxtObjectType != null) {
            objectTypeValue = ObjectType.fromName(ctxtObjectType);
            return ManagedObject.getClassFromRegistry(encodingType, objectTypeValue);
        }

        return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
    }
}
