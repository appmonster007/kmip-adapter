package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.SplitKeyParts;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class SplitKeyPartsXmlSerializer extends AbstractKmipXmlSerializer<SplitKeyParts, Integer> {

    public SplitKeyPartsXmlSerializer() {
        super(SplitKeyParts::getValue);
    }
}