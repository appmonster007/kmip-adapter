package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;

public class RecommendedCurveJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RecommendedCurve, String> {

    public RecommendedCurveJsonSerializer() {
        super(RecommendedCurve::getDescription);
    }
}