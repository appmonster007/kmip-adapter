package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;

public class CancellationResultXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CancellationResult, String> {

    public CancellationResultXmlSerializer() {
        super(CancellationResult::getDescription);
    }
}