package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.Qlength;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.CryptographicDomainParameters;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CryptographicDomainParametersTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CryptographicDomainParameters> {
    private final KmipTag kmipTag = CryptographicDomainParameters.kmipTag;
    private final EncodingType encodingType = CryptographicDomainParameters.encodingType;

    @Override
    public CryptographicDomainParameters deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        CryptographicDomainParameters.CryptographicDomainParametersBuilder builder = CryptographicDomainParameters.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        CryptographicDomainParameters cryptographicdomainparameters = builder.build();
        if (!cryptographicdomainparameters.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", cryptographicdomainparameters.getClass().getSimpleName(), spec));
        }
        return cryptographicdomainparameters;
    }

    private void setValue(
            CryptographicDomainParameters.CryptographicDomainParametersBuilder builder,
            KmipTag.Value nodeTag,
            TtlvObject ttlvObject,
            TtlvMapper mapper
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.QLENGTH ->
                    builder.qlength(mapper.readValue(ttlvObject.toByteBuffer(), Qlength.class));
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(mapper.readValue(ttlvObject.toByteBuffer(), RecommendedCurve.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}