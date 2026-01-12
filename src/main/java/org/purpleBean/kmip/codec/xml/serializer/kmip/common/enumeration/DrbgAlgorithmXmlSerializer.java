package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmXmlSerializer() {
        super(DrbgAlgorithm::getDescription);
    }
}