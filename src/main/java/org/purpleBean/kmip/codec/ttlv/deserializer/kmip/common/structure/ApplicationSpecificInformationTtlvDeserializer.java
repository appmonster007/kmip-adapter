package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.common.structure.ApplicationSpecificInformation;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ApplicationSpecificInformationTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ApplicationSpecificInformation> {
    private final KmipTag kmipTag = ApplicationSpecificInformation.kmipTag;
    private final EncodingType encodingType = ApplicationSpecificInformation.encodingType;

    @Override
    public ApplicationSpecificInformation deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder = ApplicationSpecificInformation.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        ApplicationSpecificInformation applicationspecificinformation = builder.build();
        if (!applicationspecificinformation.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", applicationspecificinformation.getClass().getSimpleName(), spec));
        }
        return applicationspecificinformation;
    }

    private void setValue(
            ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder,
            KmipTag.Value nodeTag,
            TtlvObject ttlvObject,
            TtlvMapper mapper
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.APPLICATION_NAMESPACE ->
                    builder.applicationNamespace(mapper.readValue(ttlvObject.toByteBuffer(), ApplicationNamespace.class));
            case KmipTag.Standard.APPLICATION_DATA ->
                    builder.applicationData(mapper.readValue(ttlvObject.toByteBuffer(), ApplicationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}