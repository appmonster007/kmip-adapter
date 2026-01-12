package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.Qlength;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.CryptographicDomainParameters;

import java.io.IOException;

public class CryptographicDomainParametersXmlDeserializer extends AbstractKmipStructureXmlDeserializer<CryptographicDomainParameters, CryptographicDomainParameters.CryptographicDomainParametersBuilder> {

    public CryptographicDomainParametersXmlDeserializer() {
        super(CryptographicDomainParameters.kmipTag);
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