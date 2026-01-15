package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmXmlSerializer() {
        super(DrbgAlgorithm::getDescription);
    }
}