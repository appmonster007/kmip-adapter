package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvXmlSerializer extends AbstractKmipXmlSerializer<RandomIv, Boolean> {

    public RandomIvXmlSerializer() {
        super(RandomIv::getValue);
    }
}