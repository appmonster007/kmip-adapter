package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RecommendedCurve, String> {

    public RecommendedCurveJsonSerializer() {
        super(RecommendedCurve::getDescription);
    }
}