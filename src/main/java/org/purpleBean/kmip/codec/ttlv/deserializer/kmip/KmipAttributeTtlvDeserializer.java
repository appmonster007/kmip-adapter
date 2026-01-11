package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;

public class KmipAttributeTtlvDeserializer<T extends KmipAttribute> extends KmipDataTypeTtlvDeserializer<KmipAttribute> {

    @Override
    public T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject ttlvObject = TtlvObject.fromBuffer(ttlvBuffer);
        KmipTag.Value kmipTagValue = KmipTag.fromBytes(KmipContext.getSpec(), ttlvObject.getTag());
        EncodingType encodingType = EncodingType.fromTypeValue(ttlvObject.getType()).orElse(null);

        if (kmipTagValue == null || encodingType == null) {
            return null;
        }

        Class<? extends KmipAttribute> attributeClass = KmipAttribute.getClassFromRegistry(kmipTagValue, encodingType);
        if (attributeClass == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTagValue.getValue(), encodingType));
        }

        ttlvBuffer.rewind();
        return (T) mapper.readValue(ttlvBuffer, attributeClass);
    }
}