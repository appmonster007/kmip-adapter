package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmXmlDeserializer() {
        super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType, String.class, value -> new DrbgAlgorithm(DrbgAlgorithm.fromName(value)));
    }
}