package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;

public class ResultReasonXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ResultReason, String> {

    public ResultReasonXmlDeserializer() {
        super(ResultReason.kmipTag, ResultReason.encodingType, String.class, value -> new ResultReason(ResultReason.fromName(value)));
    }
}