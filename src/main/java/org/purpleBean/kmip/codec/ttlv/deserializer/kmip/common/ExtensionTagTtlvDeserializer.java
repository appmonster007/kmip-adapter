package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ExtensionTag;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ExtensionTagTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ExtensionTag> {
    private final KmipTag kmipTag = ExtensionTag.kmipTag;
    private final EncodingType encodingType = ExtensionTag.encodingType;

    @Override
    public ExtensionTag deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        Integer value = mapper.readValue(bb, Integer.class);
        ExtensionTag extensionTag = ExtensionTag.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!extensionTag.isSupported()) {
            throw new NoSuchElementException(String.format("ExtensionTag not supported for spec %s", spec));
        }
        return extensionTag;
    }
}