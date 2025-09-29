package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * TTLV deserializer for DataEnumeration.
 */
public class DataEnumerationTtlvDeserializer extends KmipDataTypeTtlvDeserializer<DataEnumeration> {
    private final KmipTag kmipTag = DataEnumeration.kmipTag;
    private final EncodingType encodingType = DataEnumeration.encodingType;

    @Override
    public DataEnumeration deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for DataEnumeration", encodingType.getTypeValue()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        DataEnumeration dataenumeration = new DataEnumeration(DataEnumeration.fromValue(value));

        if (!dataenumeration.isSupported()) {
            throw new NoSuchElementException(
                String.format("DataEnumeration '%d' not supported for spec %s", value, spec));
        }
        return dataenumeration;
    }
}
