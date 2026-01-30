package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RecommendedCurveTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RecommendedCurve, RecommendedCurve.RecommendedCurveBuilder> {

    public RecommendedCurveTtlvDeserializer() {
        super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType);
    }

    @Override
    protected RecommendedCurve.RecommendedCurveBuilder createBuilder() {
        return RecommendedCurve.builder();
    }

    @Override
    protected void setValue(RecommendedCurve.RecommendedCurveBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(RecommendedCurve.fromValue(value));
    }

    @Override
    protected RecommendedCurve build(RecommendedCurve.RecommendedCurveBuilder builder) {
        return builder.build();
    }
}
