package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.RngAlgorithm;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class RngAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<RngAlgorithm, String> {

    public RngAlgorithmXmlSerializer() {
        super(RngAlgorithm::getDescription);
    }
}