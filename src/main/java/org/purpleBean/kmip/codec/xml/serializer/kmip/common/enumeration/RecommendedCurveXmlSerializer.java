package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RecommendedCurve, String> {

    public RecommendedCurveXmlSerializer() {
        super(RecommendedCurve::getDescription);
    }
}