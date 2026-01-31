package org.purpleBean.kmip.codec.ttlv.deserializer.api;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * TTLV deserializer for {@link KmipAttribute} objects.
 * <p>
 * This class extends {@link KmipDataTypeTtlvDeserializer} to handle the specific logic required
 * for deserializing KMIP Attributes from TTLV. It delegates the class lookup to the
 * {@link KmipAttribute} registry.
 */
public class KmipAttributeTtlvDeserializer extends KmipDataTypeTtlvDeserializer<KmipAttribute> {

    @Override
    public KmipAttribute deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return super.deserialize(ttlvBuffer, mapper);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, TtlvMapper mapper) {
        return KmipAttribute.getClassFromRegistry(kmipTag, encodingType);
    }
}
