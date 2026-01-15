package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Qlength;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.CryptographicDomainParameters;

import java.io.IOException;

public class CryptographicDomainParametersJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CryptographicDomainParameters, CryptographicDomainParameters.CryptographicDomainParametersBuilder> {

    public CryptographicDomainParametersJsonDeserializer() {
        super(CryptographicDomainParameters.kmipTag, CryptographicDomainParameters.encodingType);
    }

    @Override
    protected CryptographicDomainParameters.CryptographicDomainParametersBuilder createBuilder() {
        return CryptographicDomainParameters.builder();
    }

    @Override
    protected void setValue(CryptographicDomainParameters.CryptographicDomainParametersBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.QLENGTH -> builder.qlength(ctxt.readValue(p, Qlength.class));
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CryptographicDomainParameters build(CryptographicDomainParameters.CryptographicDomainParametersBuilder builder) {
        return builder.build();
    }
}