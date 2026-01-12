package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodXmlSerializer extends AbstractKmipXmlSerializer<SplitKeyMethod, String> {

    public SplitKeyMethodXmlSerializer() {
        super(SplitKeyMethod::getDescription);
    }
}