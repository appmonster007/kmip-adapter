package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;

public class KmipDataTypeTtlvDeserializer<T extends KmipDataType> extends TtlvDeserializer<KmipDataType> {

    @Override
    public T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject ttlvObject = TtlvObject.fromBuffer(ttlvBuffer);
        KmipTag.Value kmipTagValue = KmipTag.fromBytes(ttlvObject.getTag());
        EncodingType encodingType = EncodingType.fromTypeValue(ttlvObject.getType()).orElse(null);

        if (kmipTagValue == null || encodingType == null) {
            return null;
        }

        Class<? extends KmipDataType> clazz = getKmipDataTypeClass(kmipTagValue, encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTagValue.getValue(), encodingType));
        }

        ttlvBuffer.rewind();
        return (T) mapper.readValue(ttlvBuffer, clazz);
    }

    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType) {
        return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
    }
}