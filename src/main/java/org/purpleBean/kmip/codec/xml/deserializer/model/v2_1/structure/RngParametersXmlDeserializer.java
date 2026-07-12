package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.type.PredictionResistance;

public class RngParametersXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RngParameters, RngParameters.RngParametersBuilder> {

    public RngParametersXmlDeserializer() {
        super(RngParameters.kmipTag, RngParameters.encodingType);
    }

    @Override
    protected RngParameters.RngParametersBuilder createBuilder() {
        return RngParameters.builder();
    }

    @Override
    protected void setValue(RngParameters.RngParametersBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.RNG_ALGORITHM -> builder.rngAlgorithm(ctxt.readValue(p, RngAlgorithm.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM -> builder.cryptographicAlgorithm(ctxt.readValue(p, CryptographicAlgorithm.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_LENGTH -> builder.cryptographicLength(ctxt.readValue(p, CryptographicLength.class));
            case KmipTag.Standard.HASHING_ALGORITHM -> builder.hashingAlgorithm(ctxt.readValue(p, HashingAlgorithm.class));
            case KmipTag.Standard.DRBG_ALGORITHM -> builder.drbgAlgorithm(ctxt.readValue(p, DrbgAlgorithm.class));
            case KmipTag.Standard.RECOMMENDED_CURVE -> builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
            case KmipTag.Standard.FIPS186_VARIATION -> builder.fips186Variation(ctxt.readValue(p, Fips186Variation.class));
            case KmipTag.Standard.PREDICTION_RESISTANCE -> builder.predictionResistance(ctxt.readValue(p, PredictionResistance.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RngParameters build(RngParameters.RngParametersBuilder builder) {
        return builder.build();
    }
}