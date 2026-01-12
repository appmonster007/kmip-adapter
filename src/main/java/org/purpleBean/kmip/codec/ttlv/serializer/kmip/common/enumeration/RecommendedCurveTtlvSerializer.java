package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveTtlvSerializer extends AbstractKmipTtlvSerializer<RecommendedCurve, Integer> {

    public RecommendedCurveTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}