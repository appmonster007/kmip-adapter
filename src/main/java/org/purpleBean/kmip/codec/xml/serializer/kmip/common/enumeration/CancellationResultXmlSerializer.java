package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CancellationResult, String> {

    public CancellationResultXmlSerializer() {
        super(CancellationResult::getDescription);
    }
}