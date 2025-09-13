package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SampleStructureTtlvDeserializer implements TtlvDeserializer<SampleStructure> {
    EncodingType type = EncodingType.STRUCTURE;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.SECRET_DATA);

    @Override
    public SampleStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());

        KmipSpec spec = KmipContext.getSpec();
        SampleStructure.SampleStructureBuilder builder = SampleStructure.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        SampleStructure sampleStructure = builder.build();

        if (!sampleStructure.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return sampleStructure;
    }

    private void setValue(SampleStructure.SampleStructureBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE ->
                    builder.activationDate(mapper.readValue(ttlvObject.toByteBuffer(), ActivationDateAttribute.class));
            case KmipTag.Standard.STATE -> builder.state(mapper.readValue(ttlvObject.toByteBuffer(), State.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
