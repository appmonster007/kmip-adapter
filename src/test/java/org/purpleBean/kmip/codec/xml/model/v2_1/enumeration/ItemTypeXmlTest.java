package org.purpleBean.kmip.codec.xml.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.enumeration.ItemType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ItemType Xml Serialization Tests")
class ItemTypeXmlTest extends AbstractXmlSerializationTestSuite<ItemType> {

    @Override
    public Class<ItemType> type() {
        return ItemType.class;
    }

    @Override
    public ItemType createDefault() {
        return ItemType.Standard.values()[0].inst();
    }

    @Override
    public ItemType createVariant() {
        return ItemType.Standard.values()[1].inst();
    }
}