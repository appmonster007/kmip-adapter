package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Function;

public abstract class AbstractKmipDataTypeTtlvSerializer<T extends KmipDataType, V> extends KmipDataTypeTtlvSerializer<T> {

    private final Function<T, V> valueExtractor;

    protected AbstractKmipDataTypeTtlvSerializer(Function<T, V> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public ByteBuffer serialize(T value, TtlvMapper mapper) throws IOException {
        if (value == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IOException(
                    String.format("%s is not supported for KMIP spec %s",
                            value.getKmipTag().getDescription(), spec)
            );
        }

        V rawValue = valueExtractor.apply(value);
        byte[] payload = mapper.writeValueAsByteBuffer(rawValue).array();

        return TtlvObject.builder()
                .tag(value.getKmipTag().getTagBytes())
                .type(value.getEncodingType().getTypeValue())
                .value(payload)
                .build()
                .toByteBuffer();
    }
}