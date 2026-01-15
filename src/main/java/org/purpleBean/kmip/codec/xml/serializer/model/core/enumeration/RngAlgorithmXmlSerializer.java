package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RngAlgorithm, String> {

    public RngAlgorithmXmlSerializer() {
        super(RngAlgorithm::getDescription);
    }
}