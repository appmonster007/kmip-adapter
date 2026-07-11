package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.ProtectionStorageMasks;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProtectionStorageMasks Xml Serialization Tests")
class ProtectionStorageMasksXmlTest extends AbstractXmlSerializationTestSuite<ProtectionStorageMasks> {

    @Override
    public Class<ProtectionStorageMasks> type() {
        return ProtectionStorageMasks.class;
    }

    @Override
    public ProtectionStorageMasks createDefault() {
        return ProtectionStorageMasks.builder().build();
    }

    @Override
    public ProtectionStorageMasks createVariant() {
        return ProtectionStorageMasks.builder().build();
    }
}