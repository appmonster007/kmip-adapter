package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.State;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class StateTtlvDeserializer implements TtlvDeserializer<State> {
    EncodingType type = EncodingType.ENUMERATION;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.STATE);

    @Override
    public State deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = bb.getInt();

        KmipSpec spec = KmipCodecContext.getSpec();
        State state = new State(State.fromValue(spec, value));

        if (!state.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return state;
    }
}
