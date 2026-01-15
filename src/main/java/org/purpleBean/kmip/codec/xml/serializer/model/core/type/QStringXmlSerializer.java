package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.QString;

import java.nio.ByteBuffer;

public class QStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<QString, ByteBuffer> {

    public QStringXmlSerializer() {
        super(QString::getValue);
    }
}