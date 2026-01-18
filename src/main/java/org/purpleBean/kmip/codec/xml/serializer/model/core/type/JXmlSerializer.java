package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.J;

import java.math.BigInteger;

public class JXmlSerializer extends AbstractKmipDataTypeXmlSerializer<J, BigInteger> {

    public JXmlSerializer() {
        super(J::getValue);
    }
}