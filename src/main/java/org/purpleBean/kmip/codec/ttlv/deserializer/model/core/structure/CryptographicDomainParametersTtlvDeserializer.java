package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.CryptographicDomainParameters;
import org.purpleBean.kmip.model.core.type.Qlength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CryptographicDomainParametersTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CryptographicDomainParameters, CryptographicDomainParameters.CryptographicDomainParametersBuilder> {

    public CryptographicDomainParametersTtlvDeserializer() {
        super(CryptographicDomainParameters.kmipTag, CryptographicDomainParameters.encodingType);
    }

    @Override
    protected CryptographicDomainParameters.CryptographicDomainParametersBuilder createBuilder() {
        return CryptographicDomainParameters.builder();
    }

    @Override
    protected void setValue(CryptographicDomainParameters.CryptographicDomainParametersBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.QLENGTH -> builder.qlength(mapper.readValue(p, Qlength.class));
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CryptographicDomainParameters build(CryptographicDomainParameters.CryptographicDomainParametersBuilder builder) {
        return builder.build();
    }
}