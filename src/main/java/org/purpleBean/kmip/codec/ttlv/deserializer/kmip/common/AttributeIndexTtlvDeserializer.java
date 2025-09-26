package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class AttributeIndexTtlvDeserializer extends KmipDataTypeTtlvDeserializer<Attribute.AttributeIndex> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_INDEX);
    private final EncodingType encodingType = EncodingType.INTEGER;

    @Override
    public Attribute.AttributeIndex deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", encodingType.getTypeValue(), kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int index = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        Attribute.AttributeIndex datatype = Attribute.AttributeIndex.of(index);

        if (!datatype.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return datatype;
    }
}
