package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class DrbgAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmXmlSerializer() {
        super(DrbgAlgorithm::getDescription);
    }
}