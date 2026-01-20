package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;

public class CancellationResultXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CancellationResult, String> {

    public CancellationResultXmlDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType, String.class, value -> CancellationResult.fromName(value).inst());
    }
}