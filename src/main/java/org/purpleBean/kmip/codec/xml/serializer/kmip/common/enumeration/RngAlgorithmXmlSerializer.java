package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

public class RngAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RngAlgorithm, String> {

    public RngAlgorithmXmlSerializer() {
        super(RngAlgorithm::getDescription);
    }
}