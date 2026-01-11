package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsXmlDeserializer extends AbstractKmipXmlDeserializer<SplitKeyParts, Integer> {

    public SplitKeyPartsXmlDeserializer() {
        super(SplitKeyParts.kmipTag, SplitKeyParts.encodingType, Integer.class, value -> SplitKeyParts.builder().value(value).build());
    }
}